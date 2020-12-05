package edu.khai.healthrecommendationsservice.testdataproducer

import org.springframework.boot.CommandLineRunner
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.core.KafkaTemplate
import java.lang.Thread.sleep

@SpringBootApplication
class TestDataProducerApplication(
    val generator: RandomDataGenerator,
    val template: KafkaTemplate<String, Any>
) : CommandLineRunner {

    override fun run(vararg args: String?) {
        while (true) {
            val metrics = generator.generateRandomMetrics()
            template.send("events_iot_json_v1", metrics)
            sleep(100)
        }
    }

}

fun main(args: Array<String>) {
    runApplication<TestDataProducerApplication>(*args)
}
