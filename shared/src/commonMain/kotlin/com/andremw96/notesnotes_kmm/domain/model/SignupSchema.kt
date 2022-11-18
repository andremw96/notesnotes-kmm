package com.andremw96.notesnotes_kmm.domain.model

import com.andremw96.notesnotes_kmm.network.model.signup.SignupResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource

data class SignupSchema(
    val createdAt: String,
    val email: String,
    val firstName: String,
    val fullName: String,
    val lastName: String,
    val notesCount: Int,
    val updatedAt: String,
    val userId: Int,
    val username: String
) {
    companion object {
        private fun default(): SignupSchema = SignupSchema(
            "",
            "",
            "",
            "",
            "",
            -1,
            "",
            -1,
            ""
        )
        fun responseToSchema(response: Resource<SignupResponse>): Resource<SignupSchema> {
            return when(response) {
                is Resource.Success -> Resource.Success(
                    data = response.data?.let {
                        SignupSchema(
                            createdAt = it.createdAt,
                            email = it.email,
                            firstName = it.firstName.string,
                            fullName = it.fullName.string,
                            lastName = it.lastName.string,
                            notesCount = it.notesCount,
                            updatedAt = it.updatedAt,
                            userId = it.userId,
                            username = it.username
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
