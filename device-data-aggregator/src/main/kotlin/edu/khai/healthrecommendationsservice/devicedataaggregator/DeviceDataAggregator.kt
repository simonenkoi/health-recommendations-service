package edu.khai.healthrecommendationsservice.devicedataaggregator

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DeviceDataAggregator

fun main(args: Array<String>) {
    runApplication<DeviceDataAggregator>(*args)
}
