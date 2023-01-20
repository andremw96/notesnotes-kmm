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
                prompt: viewModel.viewState.titleError
            )
            .onChange(of: viewModel.viewState.noteItem.title, perform: { char in
                viewModel.validateTitleDescription(title: char, description: viewModel.viewState.noteItem.description)
            })
            .autocapitalization(.none)
            
            Text("Description")

            ZStack {
                VStack {
                    TextEditor(text: $viewModel.viewState.noteItem.description)
                        .onChange(of: viewModel.viewState.noteItem.description, perform: { char in
                            viewModel.validateTitleDescription(title: viewModel.viewState.noteItem.title, description: char)
                        })
                    
                    Text(viewModel.viewState.descriptionError)
                        .fixedSize(horizontal: false, vertical: true)
                        .font(.caption)
                        .foregroundColor(Color.red)
                }
            }
            .padding()
            .overlay(
                RoundedRectangle(cornerRadius: 10)
                    .stroke(lineWidth: 2)
                    .foregroundColor(Color("TextFieldOutlineColor"))
            )
           
            
            Spacer()
        }
        .padding()
        .navigationTitle(isEditing ? "Edit \(note!.title)" : "Add New Note")
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing, content: {
                Button(action: {
                    
                }, label: {
                    Image(systemName: "tray.and.arrow.down.fill")
                })
            })
        }
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
