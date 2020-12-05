package edu.khai.healthrecommendationsservice.devicedataservice.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.function.client.WebClient

@Configuration
class ClientsConfig {

    @Bean
    fun recommendationService(builder: WebClient.Builder): WebClient {
        return builder.baseUrl("http://localhost:5000/").build()
    }
}