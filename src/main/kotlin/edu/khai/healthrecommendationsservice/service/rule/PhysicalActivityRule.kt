package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class PhysicalActivityRule : Rule<Pair<Int?, Int?>>() {
    override fun evaluateMetric(metrics: Metrics): Pair<Int?, Int?> {
        return metrics.physicalFrequency to metrics.physicalState
    }

    override fun evaluateRecommendation(metric: Pair<Int?, Int?>): String? {
        val (physicalFrequency, physicalState) = metric
        return when {
            physicalFrequency == null || physicalState == null -> null
            physicalFrequency in 0..2 && physicalState in 0..2 -> "rule.physical-activity.low-activity-bad-state"
            physicalFrequency in 0..2 && physicalState == 3 -> "rule.physical-activity.low-activity-norm-state"
            physicalFrequency in 0..2 && physicalState in 4..5 -> "rule.physical-activity.low-activity-good-state"
            physicalFrequency == 3 && physicalState in 0..2 -> "rule.physical-activity.norm-activity-bad-state"
            physicalFrequency == 3 && physicalState == 3 -> "rule.physical-activity.norm-activity-norm-state"
            physicalFrequency == 3 && physicalState in 4..5 -> "rule.physical-activity.norm-activity-good-state"
            physicalFrequency in 4..5 && physicalState in 0..2 -> "rule.physical-activity.high-activity-bad-state"
            physicalFrequency in 4..5 && physicalState == 3 -> "rule.physical-activity.high-activity-norm-state"
            else -> "rule.sleep-duration.high-activity-good-state"
        }
    }
}