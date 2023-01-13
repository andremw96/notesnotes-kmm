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
    let dateFormatterGet = DateFormatter()
    dateFormatterGet.dateFormat = "yyyy-MM-dd'T'HH:mm:ss.SSSSSS'Z'"

    let dateFormatterPrint = DateFormatter()
    dateFormatterPrint.dateFormat = "E, d MMM yyyy HH:mm"
    
    let date = (dateFormatterGet.date(from: schema.updatedAt) ?? dateFormatterGet.date(from: "1970-01-01T00:00:00+0000"))!
    
    return NoteItem(
        createdAt: schema.createdAt,
        description: schema.description_,
        id: Int(schema.id),
        isDeleted: schema.isDeleted,
        title: schema.title,
        updatedAt: dateFormatterPrint.string(from: date),
        userId: Int(schema.userId)
    )
}

