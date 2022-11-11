package com.andremw96.notesnotes_kmm.domain.model

import com.andremw96.notesnotes_kmm.network.model.listnotes.response.DeleteNoteResponse
import com.andremw96.notesnotes_kmm.network.model.listnotes.response.Description
import com.andremw96.notesnotes_kmm.network.utils.Resource

data class DeleteNoteSchema(
    val createdAt: String,
    val description: Description,
    val id: Int,
    val isDeleted: Boolean,
    val title: String,
    val updatedAt: String,
    val userId: Int
) {
    companion object {
        private fun default(): DeleteNoteSchema = DeleteNoteSchema(
            createdAt = "",
            description = Description("", true),
            id = -1,
            isDeleted = false,
            title = "",
            updatedAt = "",
            userId = -1
        )

        fun responseToSchema(response: Resource<DeleteNoteResponse>): Resource<DeleteNoteSchema> {
            return when (response) {
                is Resource.Success -> Resource.Success(
                    data = response.data?.let {
                        DeleteNoteSchema(
                            createdAt = it.createdAt,
                            description = it.description,
                            id = it.id,
                            isDeleted = it.isDeleted,
                            title = it.title,
                            updatedAt = it.updatedAt,
                            userId = it.userId
                        )
                    } ?: default()
                )
                is Resource.Error -> Resource.Error(
                    errorMessage = response.message ?: "Something went wrong"
                )
                is Resource.Loading -> Resource.Loading()
            }
        }
    }
}
