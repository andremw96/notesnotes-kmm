package com.andremw96.notesnotes_kmm.network

import com.andremw96.notesnotes_kmm.network.model.LoginRequest
import com.andremw96.notesnotes_kmm.network.model.LoginUserResponse
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class NotesNotesServiceImpl : NotesNotesService {
    private val client = HttpClient() {
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
    private val API_URL = "http://192.168.100.4:8080"

    override suspend fun login(username: String, password: String): HttpResponse {
        val url = "$API_URL/user/login"
        val loginRequest = LoginRequest(username, password)
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }

        return response
    }
}
