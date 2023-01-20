//
//  ViewModelProvider.swift
//  iosApp
//
//  Created by Quipper Indonesia on 23/12/22.
//  Copyright © 2022 orgName. All rights reserved.
//

import Foundation
import shared


class ViewModelProvider {
    private static let instance = ViewModelProvider()
    
    let useCaseProvider = UseCaseProvider.getInstance()
    
    static func getInstance() -> ViewModelProvider {
        return instance
    }
    
    @MainActor func provideLoginViewModel() -> LoginViewModel {
        let loginDataValidator = useCaseProvider.loginDataValidator
        let loginUsecase = useCaseProvider.loginUseCase
        let saveCredential = useCaseProvider.saveCredential
        let getCredential = useCaseProvider.getCredential
        
        return LoginViewModel(loginDataValidator: loginDataValidator, loginUseCase: loginUsecase, saveCredential: saveCredential, getCredential: getCredential)
    }
    
    @MainActor func provideNoteListViewModel() -> NoteListViewModel {
        let logoutUseCase = useCaseProvider.logoutUseCase
        let fetchListNote = useCaseProvider.fetchListNotes
        let deleteNote = useCaseProvider.deleteNote
        
        return NoteListViewModel(logoutUseCase: logoutUseCase, fetchListNote: fetchListNote, deleteNote: deleteNote)
    }
    
    @MainActor func provideAddEditNoteViewModel() -> AddEditNoteViewModel {
        let addEditNoteDataValidator = useCaseProvider.addEditNoteDataValidator
        let saveNewNote = useCaseProvider.saveNewNote
        let updateNote = useCaseProvider.updateNote
        let logoutUseCase = useCaseProvider.logoutUseCase
        
        return AddEditNoteViewModel(logoutUseCase: logoutUseCase, addEditNoteDataValidator: addEditNoteDataValidator, saveNewNote: saveNewNote, updateNote: updateNote)
    }
    
}
