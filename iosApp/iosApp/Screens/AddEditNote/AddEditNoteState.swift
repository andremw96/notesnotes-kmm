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
    var saveNoteSuccess = false
    var saveNoteError = ""
}
