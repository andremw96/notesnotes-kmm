//
//  NoteListViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class NoteListViewModel: ObservableObject {
    @Published var viewState: NoteListState = NoteListState()
    
    private let logoutUseCase: Logout
    private let fetchListNote: FetchListNote
    private let deleteNote: DeleteNote
    
    init(logoutUseCase: Logout, fetchListNote: FetchListNote, deleteNote: DeleteNote) {
        self.logoutUseCase = logoutUseCase
        self.fetchListNote = fetchListNote
        self.deleteNote = deleteNote
    }
    
    func logoutFromApps() async {
        do {
            try await logoutUseCase.invoke()
        } catch {
            print(error)
        }
    }
    
    private func handleResponseError(responseMessage: String) -> NoteListState {
        if (responseMessage.contains("Unauthorized")) {
            return NoteListState(
                isLoading: false,
                listData: viewState.listData,
                error: "",
                authenticationError: responseMessage
            )
        } else {
            return NoteListState(
                isLoading: false,
                listData: viewState.listData,
                error: responseMessage,
                authenticationError: ""
            )
        }
    }
    
    func fetchData() async {
        do {
            let response = try await fetchListNote.invoke()
            
            if (response is ResourceLoading) {
                viewState = NoteListState(
                    isLoading: true,
                    listData: [],
                    error: "",
                    authenticationError: ""
                )
            } else if (response is ResourceError) {
                let responseMessage = response.message ?? ""
                
                viewState = handleResponseError(responseMessage: responseMessage)
            } else if (response is ResourceSuccess) {
                viewState = NoteListState(
                    isLoading: false,
                    listData: response.data!.map { schema in
                        listNoteSchemaToItem(schema: schema as! ListNoteSchema)
                    },
                    error: "",
                    authenticationError: ""
                )
            } else {
                viewState = NoteListState()
            }
        } catch {
            viewState = NoteListState(
                isLoading: false,
                listData: viewState.listData,
                error: error.localizedDescription,
                authenticationError: ""
            )
        }
    }
    
    func deleteNoteData(indexSet: IndexSet) async {
        let latestData = viewState.listData
        
        do {
            let note = latestData[indexSet.first!]
            let response = try await deleteNote.invoke(noteId: Int32(note.id))
            
            if (response is ResourceLoading) {
                viewState = NoteListState(
                    isLoading: true,
                    listData: [],
                    error: "",
                    authenticationError: ""
                )
            } else if (response is ResourceError) {
                let responseMessage = response.message ?? ""
                
                viewState = handleResponseError(responseMessage: responseMessage)
            } else if (response is ResourceSuccess) {
                var newDataState = viewState.listData
                newDataState.remove(atOffsets: indexSet)

                viewState = NoteListState(
                    isLoading: false,
                    listData: newDataState,
                    error: "",
                    authenticationError: ""
                )
            } else {
                viewState = NoteListState()
            }
        } catch {
            viewState = NoteListState(
                isLoading: false,
                listData: latestData,
                error: error.localizedDescription,
                authenticationError: ""
            )
        }
    }
}
