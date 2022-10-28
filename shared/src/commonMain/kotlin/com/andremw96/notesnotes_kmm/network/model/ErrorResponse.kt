package com.andremw96.notesnotes_kmm.network.model

import kotlinx.serialization.SerialName

@kotlinx.serialization.Serializable
data class ErrorResponse(
    @SerialName("error") val error: String
)
