package com.andremw96.notesnotes_kmm.domain.model

import com.andremw96.notesnotes_kmm.network.model.addeditnote.UpdateNoteResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

data class UpdateNoteSchema(
    val createdAt: String,
    val description: String,
    val id: Int,
    val isDeleted: Boolean,
    val title: String,
    val updatedAt: String,
    val userId: Int
) {
    companion object {
        private fun default(): UpdateNoteSchema = UpdateNoteSchema(
            createdAt = "",
            description = "",
            id = -1,
            isDeleted = false,
            title = "",
            updatedAt = "",
            userId = -1
        )

        fun responseToSchema(response: Resource<UpdateNoteResponse>): Resource<UpdateNoteSchema> {
            return when (response) {
                is Resource.Success -> Resource.Success(
                    data = response.data?.let {
                        UpdateNoteSchema(
                            createdAt = it.createdAt,
                            description = it.description.string,
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
