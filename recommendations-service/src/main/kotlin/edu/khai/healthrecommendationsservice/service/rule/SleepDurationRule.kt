package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class SleepDurationRule : Rule<Int?>() {
    override fun evaluateMetric(metrics: Metrics): Int? {
        return metrics.sleepDuration
    }

    override fun evaluateRecommendation(metric: Int?): String? {
        return when {
            metric == null -> null
            metric <= 5 -> "rule.sleep-duration.deficit"
            metric <= 8 -> "rule.sleep-duration.norm"
            else -> "rule.sleep-duration.excess"
        }
    }
}