package com.andremw96.notesnotes_kmm.domain.model

import com.andremw96.notesnotes_kmm.network.model.addeditnote.SaveNewNoteResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

data class SaveNewNoteSchema(
    val note: NewNoteSchema,
    val user: NewNoteUserSchema
) {
    companion object {
        fun responseToSchema(response: Resource<SaveNewNoteResponse>): Resource<SaveNewNoteSchema> {
            return when (response) {
                is Resource.Success -> Resource.Success(
                    data = response.data?.let {
                        SaveNewNoteSchema(
                            note = NewNoteSchema(
                                createdAt = it.note.createdAt,
                                description = it.note.description.string,
                                id = it.note.id,
                                isDeleted = it.note.isDeleted,
                                title = it.note.title,
                                updatedAt = it.note.updatedAt,
                                userId = it.note.userId
                            ),
                            user = NewNoteUserSchema(
                                createdAt = it.user.createdAt,
                                email = it.user.email,
                                firstName = it.user.firstName.string,
                                fullName = it.user.fullName.string,
                                lastName = it.user.lastName.string,
                                notesCount = it.user.notesCount,
                                updatedAt = it.user.updatedAt,
                                username = it.user.username
                            )
                        )
                    } ?: SaveNewNoteSchema(
                        NewNoteSchema.default(),
                        NewNoteUserSchema.default(),
                    )
                )
                is Resource.Error -> Resource.Error(
                    errorMessage = response.message ?: "Something went wrong"
                )
                is Resource.Loading -> Resource.Loading()
            }
        }
    }
}

data class NewNoteSchema(
    val createdAt: String,
    val description: String,
    val id: Int,
    val isDeleted: Boolean,
    val title: String,
    val updatedAt: String,
    val userId: Int
) {
    companion object {
        fun default(): NewNoteSchema = NewNoteSchema(
            createdAt = "",
            description = "",
            id = -1,
            isDeleted = false,
            title = "",
            updatedAt = "",
            userId = -1
        )
    }
}

data class NewNoteUserSchema(
    val createdAt: String,
    val email: String,
    val firstName: String,
    val fullName: String,
    val lastName: String,
    val notesCount: Int,
    val updatedAt: String,
    val username: String
){
    companion object {
        fun default(): NewNoteUserSchema = NewNoteUserSchema(
            createdAt = "",
            email = "",
            firstName = "",
            fullName = "",
            lastName = "",
            notesCount = -1,
            updatedAt = "",
            username = ""
        )
    }
}

