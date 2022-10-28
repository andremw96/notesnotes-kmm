package com.andremw96.notesnotes_kmm.model.repository

import com.andremw96.notesnotes_kmm.network.model.ErrorResponse
import com.andremw96.notesnotes_kmm.network.utils.Resource
import io.ktor.client.call.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.utils.io.errors.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

abstract class BaseRepository {

    suspend inline fun <reified T> safeApiCall(crossinline apiToBeCalled: suspend () -> HttpResponse): Resource<T> {
        return withContext(Dispatchers.Default) {
            try {
                val response: HttpResponse = apiToBeCalled()

                if (response.status == HttpStatusCode.OK) {
                    Resource.Success(data = response.body())
                } else {
                    val errorResponse: ErrorResponse? = convertErrorBody(response.body())
                    Resource.Error(errorResponse?.error ?: "Something went wrong")
                }
            }  catch (e: IOException) {
                Resource.Error("Please check your network connection ${e.message}")
            } catch (e: Exception) {
                Resource.Error("Something went wrong : ${e.message}")
            }
        }
    }

    suspend fun convertErrorBody(errorBody: HttpResponse): ErrorResponse? {
        return try {
            Json.decodeFromString<ErrorResponse>(errorBody.bodyAsText())
        } catch (exception: Exception) {
            null
        }
    }
}
