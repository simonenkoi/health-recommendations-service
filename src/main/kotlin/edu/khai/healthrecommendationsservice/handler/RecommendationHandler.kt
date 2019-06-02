package edu.khai.healthrecommendationsservice.handler

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.service.RecommendationService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono
import javax.validation.Validator

@Component
class RecommendationHandler constructor(
    private val recommendationService: RecommendationService,
    private val validator: Validator
) {
    fun getRecommendations(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Metrics::class.java)
            .filter { it.validate(validator) }
            .flatMap { recommendationService.getRecommendations(it).collectList() }
            .flatMap { ok().contentType(MediaType.APPLICATION_JSON).syncBody(it) }
            .switchIfEmpty(notFound().build())
    }

}
