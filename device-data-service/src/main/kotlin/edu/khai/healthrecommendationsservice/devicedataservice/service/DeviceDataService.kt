package edu.khai.healthrecommendationsservice.devicedataservice.service

import edu.khai.healthrecommendationsservice.devicedataservice.client.RecommendationServiceClient
import edu.khai.healthrecommendationsservice.devicedataservice.repository.DeviceDataRepository
import org.springframework.stereotype.Service
import reactor.core.publisher.Flux
import reactor.core.publisher.Mono

@Service
class DeviceDataService constructor(
    private val deviceDataRepository: DeviceDataRepository,
    private val client: RecommendationServiceClient,
) {

    fun getRecommendationsFromLatestDeviceData(): Flux<String> {
        return Mono.fromCallable { deviceDataRepository.findByKey("1") }
            .flatMapMany { client.getRecommendations(it) }
    }
}