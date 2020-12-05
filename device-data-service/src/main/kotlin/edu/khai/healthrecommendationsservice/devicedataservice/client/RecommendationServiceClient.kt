package edu.khai.healthrecommendationsservice.devicedataservice.client

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Flux

@Component
class RecommendationServiceClient(private val recommendationService: WebClient) {

    fun getRecommendations(metrics: Metrics): Flux<String> {
        return recommendationService.post()
            .uri("recommendation")
            .body(BodyInserters.fromValue(metrics))
            .retrieve()
            .bodyToFlux(String::class.java)
    }

}