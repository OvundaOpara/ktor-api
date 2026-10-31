package com.example

import io.ktor.client.request.*
import io.ktor.client.statement.*
import io.ktor.http.*
import io.ktor.server.testing.*
import kotlin.test.*

class ApplicationTest {

    @Test
    fun testRoot() = testApplication {
        application { module() } // Loads the server logic
        
        val response = client.get("/")
        assertEquals(HttpStatusCode.OK, response.status)
        assertEquals("Hello, World!", response.bodyAsText())
    }    

    @Test
    fun testHealth() = testApplication {
        application { module() } // Add this missing line
        
        val response = client.get("/health")
        assertEquals(HttpStatusCode.OK, response.status)
    }
}