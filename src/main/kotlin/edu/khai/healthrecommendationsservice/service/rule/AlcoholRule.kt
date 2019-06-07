package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class AlcoholRule : Rule<Int?>() {
    override fun evaluateMetric(metrics: Metrics): Int? {
        return metrics.alcohol
    }

    override fun evaluateRecommendation(metric: Int?): String? {
        return when (metric) {
            null -> null
            0 -> "rule.alcohol.norm"
            in 1..2 -> "rule.alcohol.moderate"
            in 3..4 -> "rule.alcohol.high"
            else -> "rule.alcohol.extremely-high"
        }
    }
}