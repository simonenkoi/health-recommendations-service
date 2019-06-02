package edu.khai.healthrecommendationsservice.service

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.model.RecommendationResult
import edu.khai.healthrecommendationsservice.repository.RecommendationResultRepository
import edu.khai.healthrecommendationsservice.service.rule.RuleRegistry
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class RecommendationService constructor(
    private val recommendationResultRepository: RecommendationResultRepository,
    private val ruleRegistry: RuleRegistry
) {

    fun getRecommendations(metrics: Metrics): Flux<String> {
        return Flux.merge(saveRecommendations(metrics))
            .map { it.recommendation }
    }

    private fun saveRecommendations(metrics: Metrics): List<Mono<RecommendationResult>> {
        return ruleRegistry.getRecommendations(metrics)
            .map {
                RecommendationResult(
                    metrics = metrics,
                    recommendation = it
                )
            }
            .map {
                recommendationResultRepository.save(it)
                    .onErrorResume { err -> logReactiveError(err) }
            }

    }
}