package edu.khai.healthrecommendationsservice

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import reactor.blockhound.BlockHound
import reactor.tools.agent.ReactorDebugAgent

@SpringBootApplication
class HealthRecommendationsServiceApplication

fun main(args: Array<String>) {
    ReactorDebugAgent.init()
    BlockHound.install()
    runApplication<HealthRecommendationsServiceApplication>(*args)
}
