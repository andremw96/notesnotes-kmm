//
//  AddEditNoteState.swift
//  iosApp
//
//  Created by Quipper Indonesia on 13/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct AddEditNoteState {
    var isLoading = false
    var noteItem: NoteItem = NoteItem(createdAt: "", description: "", id: -1, isDeleted: false, title: "", updatedAt: "", userId: -1)
    var titleError = ""
    var descriptionError = ""
    var isEditing = false
    var saveNoteSuccess: Bool? = nil
    var saveNoteError = ""
    var authenticationError = ""
    
    var isDataValid: Bool {
        get { return titleError == "" && descriptionError == "" && saveNoteError == "" && noteItem.title != "" && noteItem.description != "" }
    }
    
    var isSaveError: Bool {
        get { return saveNoteError != "" || (saveNoteSuccess != nil && saveNoteSuccess == false) }
    }
    
    var isAuthError: Bool {
        get { return authenticationError != "" }
    }
}
