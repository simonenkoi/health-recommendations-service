package edu.khai.healthrecommendationsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound

@SpringBootApplication
class HealthRecommendationsServiceApplication

fun main(args: Array<String>) {
    BlockHound.install()
    runApplication<HealthRecommendationsServiceApplication>(*args)
}
