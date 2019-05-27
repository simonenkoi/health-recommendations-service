package edu.khai.healthrecommendationsservice.router

import edu.khai.healthrecommendationsservice.handler.WelcomeHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.coRouter

@Component
class WelcomeRouter {

    @Bean
    fun route(welcomeHandler: WelcomeHandler) = coRouter {
        "/index".nest {
            GET("", welcomeHandler::hello)
        }
    }

}
