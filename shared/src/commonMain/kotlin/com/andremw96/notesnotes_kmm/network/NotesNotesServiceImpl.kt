package com.andremw96.notesnotes_kmm.network

import com.andremw96.notesnotes_kmm.domain.GetAccessToken
import com.andremw96.notesnotes_kmm.network.model.listnotes.request.DeleteNoteRequest
import com.andremw96.notesnotes_kmm.network.model.login.request.LoginRequest
import io.ktor.client.*
import io.ktor.client.plugins.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NotesNotesServiceImpl(
    private val getAccessToken: GetAccessToken,
) : NotesNotesService {
    private val client = HttpClient() {
        defaultRequest {
            val authToken = getAccessToken()
            authToken?.let { tokenHeader ->
                header("Authorization", "Bearer $tokenHeader")
            }
        }
        install(Logging) {
            logger = Logger.DEFAULT
            level = LogLevel.ALL
        }
        install(ContentNegotiation) {
            json(
                Json {
                    prettyPrint = true
                    isLenient = true
                }
            )
        }
    }
    private val API_URL = "http://192.168.100.12:8080"

    override suspend fun login(username: String, password: String): HttpResponse {
        val url = "$API_URL/user/login"
        val loginRequest = LoginRequest(username, password)
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }

        return response
    }

    override suspend fun fetchListNotes(userId: Int): HttpResponse {
        val url = "$API_URL/notes?user_id=$userId"
        return client.get(url)
    }

    override suspend fun deleteNote(userId: Int, noteId: Int): HttpResponse {
        val url = "$API_URL/deletenote"
        val deleteNoteRequest = DeleteNoteRequest(userId, noteId)
        val response = client.delete(url) {
            contentType(ContentType.Application.Json)
            setBody(deleteNoteRequest)
        }

        return response
    }
}
