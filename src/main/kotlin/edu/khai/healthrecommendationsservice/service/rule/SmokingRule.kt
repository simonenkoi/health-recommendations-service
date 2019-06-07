package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class SmokingRule : Rule<Int?>() {
    override fun evaluateMetric(metrics: Metrics): Int? {
        return metrics.smoking
    }

    override fun evaluateRecommendation(metric: Int?): String? {
        return when (metric) {
            null -> null
            0 -> "rule.smoking.norm"
            in 1..2 -> "rule.smoking.moderate"
            in 3..4 -> "rule.smoking.high"
            else -> "rule.smoking.extremely-high"
        }
    }
}