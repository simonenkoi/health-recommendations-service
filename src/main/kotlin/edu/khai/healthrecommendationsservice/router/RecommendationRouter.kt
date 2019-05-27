package edu.khai.healthrecommendationsservice.router

import edu.khai.healthrecommendationsservice.handler.RecommendationHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class RecommendationRouter {

    @Bean
    fun route(recommendationHandler: RecommendationHandler) = router {
        "/index".nest {
            POST("", recommendationHandler::get)
        }
    }

}
