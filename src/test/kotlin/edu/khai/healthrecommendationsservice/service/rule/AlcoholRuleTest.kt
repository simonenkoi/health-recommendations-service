package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class AlcoholRuleTest {
    private var alcoholRule: AlcoholRule = AlcoholRule()

    companion object {
        const val NORM_RECOMMENDATION: String = "rule.alcohol.norm"
        const val MODERATE_RECOMMENDATION: String = "rule.alcohol.moderate"
        const val HIGH_RECOMMENDATION: String = "rule.alcohol.high"
        const val EXTREMELY_HIGH_RECOMMENDATION: String = "rule.alcohol.extremely-high"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = alcoholRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun lowAlcoholShouldReturnCorrectRecommendation() {
        val recommendation = alcoholRule.getRecommendation(Metrics(alcohol = 0))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normAlcoholShouldReturnCorrectRecommendation() {
        val recommendation = alcoholRule.getRecommendation(Metrics(alcohol = 1))
        assertEquals(MODERATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highAlcoholShouldReturnCorrectRecommendation() {
        val recommendation = alcoholRule.getRecommendation(Metrics(alcohol = 3))
        assertEquals(HIGH_RECOMMENDATION, recommendation)
    }

    @Test
    fun extremelyHighAlcoholShouldReturnCorrectRecommendation() {
        val recommendation = alcoholRule.getRecommendation(Metrics(alcohol = 5))
        assertEquals(EXTREMELY_HIGH_RECOMMENDATION, recommendation)
    }

}