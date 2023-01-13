//
//  AddEditNoteViewModel.swift
//  iosApp
//
//  Created by Quipper Indonesia on 13/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

@MainActor class AddEditNoteViewModel: ObservableObject {
    @Published var viewState: AddEditNoteState = AddEditNoteState()
}
