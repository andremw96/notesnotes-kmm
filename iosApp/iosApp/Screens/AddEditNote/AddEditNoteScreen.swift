//
//  AddEditNoteScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 13/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AddEditNoteScreen: View {
    var isEditing: Bool
    var note: NoteItem?
    
    var body: some View {
        Text(isEditing ? "Edit \(note!.title)" : "Add New Note")
            .padding()
            .navigationTitle(isEditing ? "Edit \(note!.title)" : "Add New Note")
    }
}

struct AddEditNoteScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddEditNoteScreen(isEditing: true, note: NoteItem(createdAt: "", description: "", id: 0, isDeleted: false, title: "", updatedAt: "", userId: 0))
    }
}
