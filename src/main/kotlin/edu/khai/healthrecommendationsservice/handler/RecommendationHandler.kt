package edu.khai.healthrecommendationsservice.handler

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.service.rule.RuleRegistry
import kotlinx.coroutines.reactive.awaitSingle
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.bodyAndAwait

@Component
class RecommendationHandler constructor(private val ruleRegistry: RuleRegistry) {

    suspend fun get(request: ServerRequest): ServerResponse {
        val recommendations = request.bodyToMono(Metrics::class.java)
            .map { ruleRegistry.getRecomendations(it) }
            .awaitSingle()
        return ServerResponse
            .ok()
            .contentType(MediaType.TEXT_HTML)
            .bodyAndAwait(recommendations)
    }

}
