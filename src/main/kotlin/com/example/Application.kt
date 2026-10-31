package com.example

import io.ktor.server.request.*
import io.ktor.server.engine.*
import io.ktor.server.netty.*
import io.ktor.server.application.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import io.ktor.server.plugins.contentnegotiation.*
import io.ktor.serialization.kotlinx.json.*
import kotlinx.serialization.Serializable

@Serializable
data class Message(val id: Int, val text: String, val author: String)

@Serializable
data class CreateMessageRequest(val text: String, val author: String)

val messages = mutableListOf(
    Message(1, "Hello, World!", "System"),
    Message(2, "Welcome to Ktor", "System")
)
var nextId = 3

fun main() {
    // Points the server to the module below
    embeddedServer(Netty, port = 8080, host = "0.0.0.0", module = Application::module).start(wait = true)
}

// This makes your routes visible to both the real server and the test engine
fun Application.module() {
    install(ContentNegotiation) { 
        json() 
    }
    routing {
        get("/") {
            call.respondText("Hello, World!")
        }
        get("/health") {
            call.respondText("OK")
        }
        // Add any other routes from your lab manual here
        get("/api/messages") {
            call.respond(messages)
        }
        get("/api/messages/1") {
            call.respond(messages)
        }
    }
}