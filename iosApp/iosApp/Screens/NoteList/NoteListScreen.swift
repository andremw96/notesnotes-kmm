//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct NoteListScreen: View {
    @StateObject private var viewModel = ViewModelProvider().provideNoteListViewModel()
    
    @Environment(\.dismiss) private var dismiss
    
    var body: some View {
        let isPresentingAlert = Binding<Bool>(
            get: { self.viewModel.viewState.error != "" },
            set: { _ in self.viewModel.viewState.error = "" }
        )
        
        ZStack {
            if viewModel.viewState.isLoading {
                ProgressView()
                    .padding()
            } else {
                if viewModel.viewState.listData.isEmpty {
                    Text("Your Notes is Empty, lets add some notes")
                        .font(.system(size: 36, weight: .bold))
                } else {
                    List {
                        Section("Your Notes") {
                            ForEach(viewModel.viewState.listData) { note in
                                NavigationLink(value: note) {
                                    NoteListItem(note: note)
                                }
                            }
                            .onDelete { indexSet in
                                Task {
                                    await viewModel.deleteNoteData(indexSet: indexSet)
                                }
                            }
                        }
                    }
                    .navigationDestination(for: NoteItem.self) { note in
                        // go to detail
                    }
                }
            }
        }
        .navigationBarBackButtonHidden()
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing, content: {
                Button("Sign Out") {
                    Task {
                        await viewModel.logoutFromApps()
                        dismiss()
                    }
                }
            })

            ToolbarItem(placement: .navigationBarTrailing, content: {
                Button {

                } label: {
                    Image(systemName: "plus")
                }
            })
        }
        .alert(isPresented: isPresentingAlert, content: {
            Alert(title: Text(viewModel.viewState.error), dismissButton: .destructive(Text("Logout")) {
                Task {
                    await viewModel.logoutFromApps()
                    dismiss()
                }
            })
        })
        .onAppear {
            Task {
                await viewModel.fetchData()
            }
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteListScreen()
    }
}
