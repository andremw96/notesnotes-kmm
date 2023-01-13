//
//  AddEditNoteScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 13/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct AddEditNoteScreen: View {
    @StateObject private var viewModel = ViewModelProvider().provideAddEditNoteViewModel()
    
    var isEditing: Bool
    var note: NoteItem?
    
    var body: some View {
        VStack(alignment: .leading) {
            TextFieldWithPromptView(
                sfSymbolName: "",
                placeholder: "Title",
                text: $viewModel.viewState.noteItem.title,
                prompt: ""
            ).autocapitalization(.none)
            
            Text("Description")

            ZStack {
                TextEditor(text: $viewModel.viewState.noteItem.description)
            }
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(lineWidth: 2)
                    .foregroundColor(Color.white)
            )
           
            
            Spacer()
        }
        .padding()
        .navigationTitle(isEditing ? "Edit \(note!.title)" : "Add New Note")
        .onAppear {
            viewModel.viewState.noteItem = note ?? NoteItem(createdAt: "", description: "", id: -1, isDeleted: false, title: "", updatedAt: "", userId: -1)
        }
    }
}

struct AddEditNoteScreen_Previews: PreviewProvider {
    static var previews: some View {
        AddEditNoteScreen(isEditing: true, note: NoteItem(createdAt: "", description: "", id: 0, isDeleted: false, title: "", updatedAt: "", userId: 0))
    }
}
