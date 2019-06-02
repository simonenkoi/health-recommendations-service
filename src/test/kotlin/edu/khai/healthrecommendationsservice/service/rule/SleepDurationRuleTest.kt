package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class SleepDurationRuleTest {

    private var sleepDurationRule: SleepDurationRule = SleepDurationRule()

    companion object {
        const val EXTREME_DEFICIT_RECOMMENDATION: String = "rule.sleep-duration.extreme-deficit"
        const val DEFICIT_RECOMMENDATION: String = "rule.sleep-duration.deficit"
        const val NORM_RECOMMENDATION: String = "rule.sleep-duration.norm"
        const val EXCESS_RECOMMENDATION: String = "rule.sleep-duration.excess"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = sleepDurationRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun extremeDeficitShouldReturnCorrectRecommendation() {
        val recommendation = sleepDurationRule.getRecommendation(Metrics(sleepDuration = 4))
        assertEquals(EXTREME_DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun deficitShouldReturnCorrectRecommendation() {
        val recommendation = sleepDurationRule.getRecommendation(Metrics(sleepDuration = 6))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun normShouldReturnCorrectRecommendation() {
        val recommendation = sleepDurationRule.getRecommendation(Metrics(sleepDuration = 8))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun excessShouldReturnCorrectRecommendation() {
        val recommendation = sleepDurationRule.getRecommendation(Metrics(sleepDuration = 10))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }
}