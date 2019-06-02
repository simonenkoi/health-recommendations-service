package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class PhysicalActivityRuleTest {
    private var physicalActivityRule: PhysicalActivityRule = PhysicalActivityRule()

    companion object {
        const val LOW_ACTIVITY_BAD_STATE_RECOMMENDATION: String = "rule.physical-activity.low-activity-bad-state"
        const val LOW_ACTIVITY_NORM_STATE_RECOMMENDATION: String = "rule.physical-activity.low-activity-norm-state"
        const val LOW_ACTIVITY_GOOD_STATE_RECOMMENDATION: String = "rule.physical-activity.low-activity-good-state"
        const val NORM_ACTIVITY_BAD_STATE_RECOMMENDATION: String = "rule.physical-activity.norm-activity-bad-state"
        const val NORM_ACTIVITY_NORM_STATE_RECOMMENDATION: String = "rule.physical-activity.norm-activity-norm-state"
        const val NORM_ACTIVITY_GOOD_STATE_RECOMMENDATION: String = "rule.physical-activity.norm-activity-good-state"
        const val HIGH_ACTIVITY_BAD_STATE_RECOMMENDATION: String = "rule.physical-activity.high-activity-bad-state"
        const val HIGH_ACTIVITY_NORM_STATE_RECOMMENDATION: String = "rule.physical-activity.high-activity-norm-state"
        const val HIGH_ACTIVITY_GOOD_STATE_RECOMMENDATION: String = "rule.sleep-duration.high-activity-good-state"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun emptyFrequencyShouldReturnNullRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalState = 1))
        assertNull(recommendation)
    }

    @Test
    fun emptyStateShouldReturnNullRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 1))
        assertNull(recommendation)
    }

    @Test
    fun lowActivityBadStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 1, physicalState = 1))
        assertEquals(LOW_ACTIVITY_BAD_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun lowActivityNormStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 1, physicalState = 3))
        assertEquals(LOW_ACTIVITY_NORM_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun lowActivityGoodStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 1, physicalState = 5))
        assertEquals(LOW_ACTIVITY_GOOD_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun normActivityBadStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 3, physicalState = 1))
        assertEquals(NORM_ACTIVITY_BAD_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun normActivityNormStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 3, physicalState = 3))
        assertEquals(NORM_ACTIVITY_NORM_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun normActivityGoodStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 3, physicalState = 5))
        assertEquals(NORM_ACTIVITY_GOOD_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highActivityBadStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 5, physicalState = 1))
        assertEquals(HIGH_ACTIVITY_BAD_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highActivityNormStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 5, physicalState = 3))
        assertEquals(HIGH_ACTIVITY_NORM_STATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highActivityGoodStateShouldReturnCorrectRecommendation() {
        val recommendation = physicalActivityRule.getRecommendation(Metrics(physicalFrequency = 5, physicalState = 5))
        assertEquals(HIGH_ACTIVITY_GOOD_STATE_RECOMMENDATION, recommendation)
    }

}