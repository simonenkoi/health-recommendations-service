package edu.khai.healthrecommendationsservice.devicedataservice.router

import edu.khai.healthrecommendationsservice.devicedataservice.handler.DeviceDataHandler
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.router

@Component
class RecommendationRouter {

    @Bean
    fun route(deviceDataHandler: DeviceDataHandler) = router {
        "/recommendation".nest {
            GET("", deviceDataHandler::getRecommendations)
        }
    }

}
