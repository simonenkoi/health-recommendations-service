package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.service.RecommendationService
import org.springframework.beans.factory.annotation.Autowired

abstract class Rule<T> {

    @Autowired
    protected lateinit var recommendationService: RecommendationService

    @Autowired
    fun register(ruleRegistry: RuleRegistry) {
        ruleRegistry.register(this)
    }

    fun getRecommendation(metrics: Metrics): String {
        return evaluateRecommendation(evaluateMetric(metrics))
    }

    protected abstract fun evaluateMetric(metrics: Metrics): T
    protected abstract fun evaluateRecommendation(metric: T): String


}