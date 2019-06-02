package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertNull
import org.junit.jupiter.api.Test

internal class BmiRuleTest {

    private var bmiRule: BmiRule = BmiRule()

    companion object {
        const val DEFAULT_HEIGHT: Double = 170.0
        const val DEFICIT_RECOMMENDATION: String = "rule.bmi.deficit"
        const val NORM_RECOMMENDATION: String = "rule.bmi.norm"
        const val EXCESS_RECOMMENDATION: String = "rule.bmi.excess"
    }

    @Test
    fun emptyMetricsShouldReturnNullRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics())
        assertNull(recommendation)
    }

    @Test
    fun emptyWeightShouldReturnNullRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(height = 1.0))
        assertNull(recommendation)
    }

    @Test
    fun emptyHeightShouldReturnNullRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 1.0))
        assertNull(recommendation)
    }

    @Test
    fun severeDeficitLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 0.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun severeDeficitMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 23.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun severeDeficitUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 46.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun deficitLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 47.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun deficitMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 50.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun deficitUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 53.0, height = DEFAULT_HEIGHT))
        assertEquals(DEFICIT_RECOMMENDATION, recommendation)
    }

    @Test
    fun normLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 54.0, height = DEFAULT_HEIGHT))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 63.0, height = DEFAULT_HEIGHT))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun normUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 72.0, height = DEFAULT_HEIGHT))
        assertEquals(NORM_RECOMMENDATION, recommendation)
    }

    @Test
    fun excessLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 73.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun excessMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 79.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun excessUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 86.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun obesityLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 87.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun obesityMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 94.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun obesityUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 101.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun severeObesityLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 102.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun severeObesityMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 108.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun severeObesityUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 115.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun verySevereObesityLowerBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 116.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun verySevereObesityMiddleShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 248.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }

    @Test
    fun verySevereObesityUpperBoundShouldReturnCorrectRecommendation() {
        val recommendation = bmiRule.getRecommendation(Metrics(weight = 380.0, height = DEFAULT_HEIGHT))
        assertEquals(EXCESS_RECOMMENDATION, recommendation)
    }
}