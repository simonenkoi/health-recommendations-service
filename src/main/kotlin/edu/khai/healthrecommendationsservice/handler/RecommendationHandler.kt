package edu.khai.healthrecommendationsservice.handler

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.service.rule.RuleRegistry
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.ok
import org.springframework.web.reactive.function.server.body
import reactor.core.publisher.Mono

@Component
class RecommendationHandler constructor(private val ruleRegistry: RuleRegistry) {

    fun get(request: ServerRequest): Mono<ServerResponse> {
        return request.bodyToMono(Metrics::class.java)
            .map { ruleRegistry.getRecomendations(it) }
            .map { Mono.just(it) }
            .flatMap { ok().contentType(MediaType.TEXT_HTML).body(it) }
    }

}
