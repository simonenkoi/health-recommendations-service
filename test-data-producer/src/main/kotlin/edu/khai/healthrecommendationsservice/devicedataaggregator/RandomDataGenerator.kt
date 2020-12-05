package edu.khai.healthrecommendationsservice.devicedataaggregator

import edu.khai.healthrecommendationsservice.api.Metrics
import org.apache.commons.lang3.RandomUtils
import org.springframework.stereotype.Component

@Component
class RandomDataGenerator {

    fun generateRandomMetrics(): Metrics {
        return Metrics(
            height = RandomUtils.nextDouble(50.0, 200.0),
            weight = RandomUtils.nextDouble(30.0, 150.0),
            sleepDuration = RandomUtils.nextInt(0, 24),
            physicalFrequency = RandomUtils.nextInt(0, 5)
        )
    }
}