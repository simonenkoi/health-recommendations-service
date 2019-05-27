package edu.khai.healthrecommendationsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class HealthRecommendationsServiceApplication

fun main(args: Array<String>) {
    runApplication<HealthRecommendationsServiceApplication>(*args)
}
