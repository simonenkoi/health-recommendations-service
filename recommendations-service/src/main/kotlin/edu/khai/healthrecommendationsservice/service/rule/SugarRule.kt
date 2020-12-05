package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class SugarRule : Rule<Int?>() {
    override fun evaluateMetric(metrics: Metrics): Int? {
        return metrics.sugar
    }

    override fun evaluateRecommendation(metric: Int?): String? {
        return when (metric) {
            null -> null
            0 -> "rule.sugar.norm"
            in 1..2 -> "rule.sugar.moderate"
            in 3..4 -> "rule.sugar.high"
            else -> "rule.sugar.extremely-high"
        }
    }
}