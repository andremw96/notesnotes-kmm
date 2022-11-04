package com.andremw96.notesnotes_kmm.domain.model

import com.andremw96.notesnotes_kmm.network.model.listnotes.ListNoteResponseItem
import com.andremw96.notesnotes_kmm.network.utils.Resource

data class ListNoteSchema(
    val createdAt: String,
    val description: String,
    val id: Int,
    val isDeleted: Boolean,
    val title: String,
    val updatedAt: String,
    val userId: Int
) {
    companion object  {
        fun responseToSchema(response: Resource<List<ListNoteResponseItem>>): Resource<List<ListNoteSchema>> {
            return when (response) {
                is Resource.Success -> Resource.Success(
                    data = response.data?.map {
                        ListNoteSchema(
                            it.createdAt,
                            it.description.string,
                            it.id,
                            it.isDeleted,
                            it.title,
                            it.updatedAt,
                            it.userId
                        )
                    } ?: listOf()
                )
                is Resource.Error -> Resource.Error(
                    errorMessage = response.message ?: "Something went wrong"
                )
                is Resource.Loading -> Resource.Loading()
            }
        }
    }
}
