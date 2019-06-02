package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class BmiRule : Rule<Double?>() {
    override fun evaluateMetric(metrics: Metrics): Double? {
        return if (metrics.weight != null && metrics.height != null) {
            metrics.weight / Math.pow(metrics.height, 2.0)
        } else {
            null
        }
    }

    override fun evaluateRecommendation(metric: Double?): String {
        return when {
            metric == null -> ""
            metric < 16 -> recommendationService.getMessage("rules.bmi.severe-deficit")
            metric >= 16 && metric < 18.5 -> recommendationService.getMessage("rules.bmi.deficit")
            metric >= 18.5 && metric < 25 -> recommendationService.getMessage("rules.bmi.norm")
            metric >= 25 && metric < 30 -> recommendationService.getMessage("rules.bmi.excess")
            metric >= 30 && metric < 35 -> recommendationService.getMessage("rules.bmi.obesity")
            metric >= 35 && metric < 40 -> recommendationService.getMessage("rules.bmi.severe-obesity")
            metric >= 40 -> recommendationService.getMessage("rules.bmi.very-severe-obesity")
            else -> ""
        }
    }
}

fun main() {
    val set1 = setOf("1", "2", "12", "5", "28")
    val set2 = setOf("1", "2", "3", "4", "5", "12")
    print(set1 - set2)
}