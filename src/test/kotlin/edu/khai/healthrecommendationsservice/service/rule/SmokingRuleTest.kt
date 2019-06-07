package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class SmokingRuleTest {
    private var smokingRule: SmokingRule = SmokingRule()

    companion object {
        const val NORM_RECOMMENDATION: String = "rule.smoking.norm"
        const val MODERATE_RECOMMENDATION: String = "rule.smoking.moderate"
        const val HIGH_RECOMMENDATION: String = "rule.smoking.high"
        const val EXTREMELY_HIGH_RECOMMENDATION: String = "rule.smoking.extremely-high"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = smokingRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun lowSmokingShouldReturnCorrectRecommendation() {
        val recommendation = smokingRule.getRecommendation(Metrics(smoking = 0))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normSmokingShouldReturnCorrectRecommendation() {
        val recommendation = smokingRule.getRecommendation(Metrics(smoking = 1))
        assertEquals(MODERATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highSmokingShouldReturnCorrectRecommendation() {
        val recommendation = smokingRule.getRecommendation(Metrics(smoking = 3))
        assertEquals(HIGH_RECOMMENDATION, recommendation)
    }

    @Test
    fun extremelyHighSmokingShouldReturnCorrectRecommendation() {
        val recommendation = smokingRule.getRecommendation(Metrics(smoking = 5))
        assertEquals(EXTREMELY_HIGH_RECOMMENDATION, recommendation)
    }

}