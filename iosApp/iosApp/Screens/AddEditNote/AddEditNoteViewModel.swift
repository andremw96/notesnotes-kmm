//
//  AddEditNoteViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 13/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

@MainActor class AddEditNoteViewModel: ObservableObject {
    @Published var viewState: AddEditNoteState = AddEditNoteState()
    
    private let addEditNoteDataValidator: AddEditNoteDataValidator
    private let saveNewNote: SaveNewNote
    private let updateNote: UpdateNote
    private let logoutUseCase: Logout
    
    init(logoutUseCase: Logout, addEditNoteDataValidator: AddEditNoteDataValidator, saveNewNote: SaveNewNote, updateNote: UpdateNote) {
        self.logoutUseCase = logoutUseCase
        self.addEditNoteDataValidator = addEditNoteDataValidator
        self.saveNewNote = saveNewNote
        self.updateNote = updateNote
    }
    
    func logoutFromApps() async {
        do {
            try await logoutUseCase.invoke()
        } catch {
            print(error)
        }
    }
    
    func validateTitleDescription(title: String, description: String) {
        viewState = AddEditNoteState(
            noteItem: NoteItem(
                createdAt: viewState.noteItem.createdAt,
                description: description,
                id: viewState.noteItem.id,
                isDeleted: viewState.noteItem.isDeleted,
                title: title,
                updatedAt: viewState.noteItem.updatedAt,
                userId: viewState.noteItem.userId
            ),
            titleError: (addEditNoteDataValidator.checkTitle(title: title) as? AddEditNoteDataValidator.AddEditNoteValidatorResultError)?.message ?? "",
            descriptionError: (addEditNoteDataValidator.checkDescription(description: description) as? AddEditNoteDataValidator.AddEditNoteValidatorResultError)?.message ?? "",
            isEditing: viewState.isEditing
        )
    }
    
    private func handleResponseError(previousState: AddEditNoteState, responseMessage: String) -> AddEditNoteState {
        if (responseMessage.contains("Unauthorized")) {
            return AddEditNoteState(
                isLoading : false,
                noteItem: previousState.noteItem,
                titleError: "",
                descriptionError: "",
                isEditing: previousState.isEditing,
                saveNoteSuccess: false,
                saveNoteError: "",
                authenticationError: responseMessage
            )
        } else {
            return AddEditNoteState(
                isLoading : false,
                noteItem: previousState.noteItem,
                titleError: "",
                descriptionError: "",
                isEditing: previousState.isEditing,
                saveNoteSuccess: false,
                saveNoteError: responseMessage,
                authenticationError: ""
            )
        }
    }
    
    func saveNote(title: String, description: String,  onNewNoteSaved: () -> Void) async -> Void {
        let previousState = viewState
        
        do {
            let response = try await saveNewNote.invoke(title: title, description: description)
            
            if (response is ResourceLoading) {
                viewState = AddEditNoteState(
                    isLoading : true,
                    noteItem: previousState.noteItem,
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: false,
                    saveNoteError: ""
                )
            }
            else if (response is ResourceError) {
                let responseMessage = response.message ?? ""
                
                viewState = handleResponseError(previousState: previousState, responseMessage: responseMessage)
            }
            else if (response is ResourceSuccess) {
                let newNote = response.data?.note
                
                viewState = AddEditNoteState(
                    isLoading : false,
                    noteItem: NoteItem(
                        createdAt: newNote?.createdAt ?? "",
                        description: newNote?.description_ ?? "",
                        id: Int(newNote?.id ?? -1),
                        isDeleted: newNote?.isDeleted ?? false,
                        title: newNote?.title ?? "",
                        updatedAt: newNote?.updatedAt ?? "",
                        userId: Int(newNote?.userId ?? -1)
                    ),
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: true,
                    saveNoteError: ""
                )
                
                onNewNoteSaved()
            } else {
                viewState = AddEditNoteState(
                    isLoading : false,
                    noteItem: previousState.noteItem,
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: false,
                    saveNoteError: response.message ?? "something went wrong, please try again"
                )
            }
        } catch {
            print(error)
            
            viewState = AddEditNoteState(
                isLoading : false,
                noteItem: previousState.noteItem,
                titleError: "",
                descriptionError: "",
                isEditing: previousState.isEditing,
                saveNoteSuccess: false,
                saveNoteError: error.localizedDescription
            )
        }
    }
    
    func updateNoteWith(noteId: Int, title: String, description: String, onNoteUpdated: () -> Void) async -> Void {
        let previousState = viewState
        
        do {
            let response = try await updateNote.invoke(noteId: Int32(noteId), title: title, description: description)
            
            if (response is ResourceLoading) {
                viewState = AddEditNoteState(
                    isLoading : true,
                    noteItem: previousState.noteItem,
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: false,
                    saveNoteError: ""
                )
            }
            else if (response is ResourceError) {
                let responseMessage = response.message ?? ""
                
                viewState = handleResponseError(previousState: previousState, responseMessage: responseMessage)
            }
            else if (response is ResourceSuccess) {
                let updatedNote = response.data
                
                viewState = AddEditNoteState(
                    isLoading : false,
                    noteItem: NoteItem(
                        createdAt: updatedNote?.createdAt ?? "",
                        description: updatedNote?.description_ ?? "",
                        id: Int(updatedNote?.id ?? -1),
                        isDeleted: updatedNote?.isDeleted ?? false,
                        title: updatedNote?.title ?? "",
                        updatedAt: updatedNote?.updatedAt ?? "",
                        userId: Int(updatedNote?.userId ?? -1)
                    ),
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: true,
                    saveNoteError: ""
                )
                
                onNoteUpdated()
            } else {
                viewState = AddEditNoteState(
                    isLoading : false,
                    noteItem: previousState.noteItem,
                    titleError: "",
                    descriptionError: "",
                    isEditing: previousState.isEditing,
                    saveNoteSuccess: false,
                    saveNoteError: response.message ?? "something went wrong, please try again"
                )
            }
        } catch {
            print(error)
            
            viewState = AddEditNoteState(
                isLoading : false,
                noteItem: previousState.noteItem,
                titleError: "",
                descriptionError: "",
                isEditing: previousState.isEditing,
                saveNoteSuccess: false,
                saveNoteError: error.localizedDescription
            )
        }
    }
}
