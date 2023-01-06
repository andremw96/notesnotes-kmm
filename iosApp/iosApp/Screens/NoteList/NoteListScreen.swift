//
//  NoteListScreen.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct NoteListScreen: View {
    var body: some View {
        List {
            Text("List Note")
        }
        .navigationBarBackButtonHidden()
        .toolbar {
            ToolbarItem(placement: .navigationBarTrailing, content: {
                Button("Sign Out") {
                    
                }
            })
            
            ToolbarItem(placement: .navigationBarTrailing, content: {
                Button {
                    
                } label: {
                    Image(systemName: "plus")
                }
            })
        }
    }
}

struct NoteListScreen_Previews: PreviewProvider {
    static var previews: some View {
        NoteListScreen()
    }
}
