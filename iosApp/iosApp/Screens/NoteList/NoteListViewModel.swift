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
    
    init(logoutUseCase: Logout, fetchListNote: FetchListNote) {
        self.logoutUseCase = logoutUseCase
        self.fetchListNote = fetchListNote
    }
    
    func logoutFromApps() async {
        do {
            try await logoutUseCase.invoke()
        } catch {
            print(error)
        }
    }
    
    func fetchData() async {
        do {
            let response = try await fetchListNote.invoke()
            
            if (response is ResourceLoading) {
                viewState = NoteListState(
                    isLoading: true,
                    listData: [],
                    error: ""
                )
            } else if (response is ResourceError) {
                viewState = NoteListState(
                    isLoading: false,
                    listData: [],
                    error: response.message ?? ""
                )
            } else if (response is ResourceSuccess) {
                viewState = NoteListState(
                    isLoading: false,
                    listData: response.data!.map { schema in
                        listNoteSchemaToItem(schema: schema as! ListNoteSchema)
                    },
                    error: ""
                )
            } else {
                viewState = NoteListState()
            }
        } catch {
            print(error)
        }
    }
}
