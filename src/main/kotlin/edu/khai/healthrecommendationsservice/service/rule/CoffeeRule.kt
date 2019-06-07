package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class CoffeeRule : Rule<Int?>() {
    override fun evaluateMetric(metrics: Metrics): Int? {
        return metrics.coffee
    }

    override fun evaluateRecommendation(metric: Int?): String? {
        return when (metric) {
            null -> null
            0 -> "rule.coffee.norm"
            in 1..2 -> "rule.coffee.moderate"
            in 3..4 -> "rule.coffee.high"
            else -> "rule.coffee.extremely-high"
        }
    }
}