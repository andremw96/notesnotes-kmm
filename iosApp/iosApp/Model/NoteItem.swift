//
//  NoteItem.swift
//  iosApp
//
//  Created by Quipper Indonesia on 06/01/23.
//  Copyright © 2023 orgName. All rights reserved.
//

import Foundation
import shared

struct NoteItem: Identifiable, Hashable {
    var createdAt: String
    var description: String
    var id: Int
    var isDeleted: Bool
    var title: String
    var updatedAt: String
    var userId: Int
}


func listNoteSchemaToItem(schema: ListNoteSchema) -> NoteItem {
    return NoteItem(
        createdAt: schema.createdAt,
        description: schema.description_,
        id: Int(schema.id),
        isDeleted: schema.isDeleted,
        title: schema.title,
        updatedAt: schema.updatedAt,
        userId: Int(schema.userId)
    )
}

