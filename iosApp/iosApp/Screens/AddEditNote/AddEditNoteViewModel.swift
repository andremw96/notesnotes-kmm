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
    
    init(addEditNoteDataValidator: AddEditNoteDataValidator, saveNewNote: SaveNewNote, updateNote: UpdateNote) {
        self.addEditNoteDataValidator = addEditNoteDataValidator
        self.saveNewNote = saveNewNote
        self.updateNote = updateNote
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
}
