package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics

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
            metric < 16 -> "Выраженный дефицит массы тела"
            metric >= 16 && metric < 18.5 -> "Недостаточная (дефицит) масса тела"
            metric >= 18.5 && metric < 25 -> "Норма"
            metric >= 25 && metric < 30 -> "Избыточная масса тела (предожирение)"
            metric >= 30 && metric < 35 -> "Ожирение"
            metric >= 35 && metric < 40 -> "Ожирение резкое"
            metric >= 40 -> "Очень резкое ожирение"
            else -> ""
        }
    }


}