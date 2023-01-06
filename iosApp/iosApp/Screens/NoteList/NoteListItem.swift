//
//  NoteListItem.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import SwiftUI

struct NoteListItem: View {
    var note: NoteItem
    
    var body: some View {
        VStack(alignment: .leading) {
            HStack {
                Text(note.title)
                    .font(.system(size: 20, weight: .bold))
                
                Spacer()
                
                Text(note.updatedAt)
                    .font(.system(size: 10, weight: .ultraLight))
                    
            }
            
            Text(note.description)
                .font(.system(size: 12, weight: .light))
                .lineLimit(2)
        }
        .padding()
    }
}

struct NoteListItem_Previews: PreviewProvider {
    static var previews: some View {
        NoteListItem(
            note: NoteItem(createdAt: "123123", description: "Lorem ipsum", id: 1, isDeleted: false, title: "title", updatedAt: "123123", userId: 1)
        )
    }
}
