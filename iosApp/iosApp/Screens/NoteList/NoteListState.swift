//
//  NoteListState.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation

struct NoteListState {
    var isLoading = false
    var listData: [NoteItem] = []
    var error = ""
    var authenticationError = ""
}
