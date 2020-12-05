package edu.khai.healthrecommendationsservice.devicedataaggregator

import edu.khai.healthrecommendationsservice.commons.KafkaTopics.Companion.DEVICE_DATA
import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.core.KafkaTemplate
import java.lang.Thread.sleep

@SpringBootApplication
class TestDataProducer(
    val generator: RandomDataGenerator,
    val template: KafkaTemplate<String, Any>
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        while (true) {
            val metrics = generator.generateRandomMetrics()
            template.send(DEVICE_DATA, "1", metrics)
            sleep(100)
        }
    }
}

fun main(args: Array<String>) {
    runApplication<TestDataProducer>(*args)
}
