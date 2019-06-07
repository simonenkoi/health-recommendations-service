package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class SugarRuleTest {
    private var sugarRule: SugarRule = SugarRule()

    companion object {
        const val NORM_RECOMMENDATION: String = "rule.sugar.norm"
        const val MODERATE_RECOMMENDATION: String = "rule.sugar.moderate"
        const val HIGH_RECOMMENDATION: String = "rule.sugar.high"
        const val EXTREMELY_HIGH_RECOMMENDATION: String = "rule.sugar.extremely-high"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = sugarRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun lowSugarShouldReturnCorrectRecommendation() {
        val recommendation = sugarRule.getRecommendation(Metrics(sugar = 0))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normSugarShouldReturnCorrectRecommendation() {
        val recommendation = sugarRule.getRecommendation(Metrics(sugar = 1))
        assertEquals(MODERATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highSugarShouldReturnCorrectRecommendation() {
        val recommendation = sugarRule.getRecommendation(Metrics(sugar = 3))
        assertEquals(HIGH_RECOMMENDATION, recommendation)
    }

    @Test
    fun extremelyHighSugarShouldReturnCorrectRecommendation() {
        val recommendation = sugarRule.getRecommendation(Metrics(sugar = 5))
        assertEquals(EXTREMELY_HIGH_RECOMMENDATION, recommendation)
    }

}