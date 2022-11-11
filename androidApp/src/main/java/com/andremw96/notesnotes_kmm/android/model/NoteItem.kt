package com.andremw96.notesnotes_kmm.android.model

import android.os.Bundle
import android.os.Parcelable
import androidx.navigation.NavType
import com.andremw96.notesnotes_kmm.domain.model.ListNoteSchema
import com.google.gson.Gson
import kotlinx.parcelize.Parcelize

@Parcelize
data class NoteItem(
    val createdAt: String,
    val description: String,
    val id: Int,
    val isDeleted: Boolean,
    val title: String,
    val updatedAt: String,
    val userId: Int
) : Parcelable {
    companion object {
        fun default() = NoteItem(
            createdAt = "",
            description = "",
            id = -1,
            isDeleted = false,
            title = "",
            updatedAt = "",
            userId = -1
        )
        fun schemaToItem(schema: ListNoteSchema): NoteItem {
            return NoteItem(
                createdAt = schema.createdAt,
                description = schema.description,
                id = schema.id,
                isDeleted = schema.isDeleted,
                title = schema.title,
                updatedAt = schema.updatedAt,
                userId = schema.userId
            )
        }
    }
}

class AssetParamType : NavType<NoteItem>(isNullableAllowed = false) {
    override fun get(bundle: Bundle, key: String): NoteItem? {
        return bundle.getParcelable(key)
    }

    override fun parseValue(value: String): NoteItem {
        return Gson().fromJson(value, NoteItem::class.java)
    }

    override fun put(bundle: Bundle, key: String, value: NoteItem) {
        bundle.putParcelable(key, value)
    }
}
