package edu.khai.healthrecommendationsservice.service.rule

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.stereotype.Component

@Component
class RuleRegistry {

    private val registry: MutableList<Rule<*>> = ArrayList()

    fun register(rule: Rule<*>) {
        registry.add(rule)
    }

    fun getRecommendations(metrics: Metrics): List<String> {
        return registry.mapNotNull { it.getRecommendation(metrics) }
    }
}