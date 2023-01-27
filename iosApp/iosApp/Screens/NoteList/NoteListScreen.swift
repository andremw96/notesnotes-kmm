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
        let isPresentingAlertError = Binding<Bool>(
            get: { self.viewModel.viewState.error != "" },
            set: { _ in
                self.viewModel.viewState.error = ""
            }
        )
        
        let isPresentingAlertAuthError = Binding<Bool>(
            get: { self.viewModel.viewState.authenticationError != "" },
            set: { _ in
                self.viewModel.viewState.authenticationError = ""
            }
        )
        
        ZStack {
            if viewModel.viewState.isLoading {
                ProgressView()
                    .padding()
            } else {
                if viewModel.viewState.listData.isEmpty {
                    VStack {
                        Text(viewModel.viewState.error != "" ? "Something went wrong, please refresh" : "Your Notes is Empty, lets add some notes")
                            .multilineTextAlignment(.center)
                            .font(.system(size: 36, weight: .bold))
                        
                        Button(action: {
                            Task {
                                await viewModel.fetchData()
                            }
                        }, label: {
                            Text("Refresh")
                        })
                    }
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
                        AddEditNoteScreen(
                            isEditing: true, 
                            note: note
                        )
                    }
                    .refreshable {
                        Task {
                            await viewModel.fetchData()
                        }
                    }
                }
            }
        }
        .onAppear {
            Task {
                await viewModel.fetchData()
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
                NavigationLink(destination: AddEditNoteScreen(isEditing: false)) {
                    Image(systemName: "plus")
                }
                
            })
        }
        .overlay(content: {
            EmptyView()
                .alert(isPresented: isPresentingAlertError, content: {
                    return Alert(title: Text(viewModel.viewState.error), dismissButton: .destructive(Text("OK")))
                })
        })
        .overlay(content: {
            EmptyView()
                .alert(isPresented: isPresentingAlertAuthError, content: {
                    return Alert(title: Text(viewModel.viewState.authenticationError), dismissButton: .destructive(Text("Logout")) {
                        Task {
                            await viewModel.logoutFromApps()
                            dismiss()
                        }
                    })
                })
        })
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteListScreen()
    }
}
