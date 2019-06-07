package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class CoffeeRuleTest {
    private var coffeeRule: CoffeeRule = CoffeeRule()

    companion object {
        const val NORM_RECOMMENDATION: String = "rule.coffee.norm"
        const val MODERATE_RECOMMENDATION: String = "rule.coffee.moderate"
        const val HIGH_RECOMMENDATION: String = "rule.coffee.high"
        const val EXTREMELY_HIGH_RECOMMENDATION: String = "rule.coffee.extremely-high"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = coffeeRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun lowCoffeeShouldReturnCorrectRecommendation() {
        val recommendation = coffeeRule.getRecommendation(Metrics(coffee = 0))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normCoffeeShouldReturnCorrectRecommendation() {
        val recommendation = coffeeRule.getRecommendation(Metrics(coffee = 1))
        assertEquals(MODERATE_RECOMMENDATION, recommendation)
    }

    @Test
    fun highCoffeeShouldReturnCorrectRecommendation() {
        val recommendation = coffeeRule.getRecommendation(Metrics(coffee = 3))
        assertEquals(HIGH_RECOMMENDATION, recommendation)
    }

    @Test
    fun extremelyHighCoffeeShouldReturnCorrectRecommendation() {
        val recommendation = coffeeRule.getRecommendation(Metrics(coffee = 5))
        assertEquals(EXTREMELY_HIGH_RECOMMENDATION, recommendation)
    }

}