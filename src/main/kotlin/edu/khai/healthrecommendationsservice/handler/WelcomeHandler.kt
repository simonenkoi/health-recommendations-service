package edu.khai.healthrecommendationsservice.handler

import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.renderAndAwait

@Component
class WelcomeHandler {

    suspend fun hello(request: ServerRequest): ServerResponse {
        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_HTML)
            .renderAndAwait("index")
    }

}
