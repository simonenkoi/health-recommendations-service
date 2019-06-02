package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class BmiRule : Rule<Double?>() {
    override fun evaluateMetric(metrics: Metrics): Double? {
        return if (metrics.weight != null && metrics.height != null) {
            metrics.weight / Math.pow(metrics.height / 100, 2.0)
        } else {
            null
        }
    }

    override fun evaluateRecommendation(metric: Double?): String? {
        return when {
            metric == null -> null
            metric < 18.5 -> "rule.bmi.deficit"
            metric < 25 -> "rule.bmi.norm"
            else -> "rule.bmi.excess"
        }
    }
}