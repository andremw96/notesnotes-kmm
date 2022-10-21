package com.andremw96.notesnotes_kmm.network

import com.andremw96.notesnotes_kmm.network.model.LoginRequest
import io.ktor.client.*
import io.ktor.client.plugins.contentnegotiation.*
import io.ktor.client.plugins.logging.*
import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.json.Json

class NotesNotesServiceImpl : NotesNotesService {
    private val client = HttpClient() {
        install(Logging)
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

    override suspend fun login(username: String, password: String): String {
        val url = "$API_URL/user/login"
        val loginRequest = LoginRequest(username, password)
        val response = client.post(url) {
            contentType(ContentType.Application.Json)
            setBody(loginRequest)
        }
        println("response ${response.bodyAsText()}")
        return response.bodyAsText()
    }
}
