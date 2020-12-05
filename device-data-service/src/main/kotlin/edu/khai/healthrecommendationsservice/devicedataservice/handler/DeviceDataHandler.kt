package edu.khai.healthrecommendationsservice.devicedataservice.handler

import edu.khai.healthrecommendationsservice.devicedataservice.service.DeviceDataService
import org.springframework.http.MediaType
import org.springframework.stereotype.Component
import org.springframework.web.reactive.function.server.ServerRequest
import org.springframework.web.reactive.function.server.ServerResponse
import org.springframework.web.reactive.function.server.ServerResponse.notFound
import org.springframework.web.reactive.function.server.ServerResponse.ok
import reactor.core.publisher.Mono

@Component
class DeviceDataHandler constructor(
    private val deviceDataService: DeviceDataService,
) {
    fun getRecommendations(request: ServerRequest): Mono<ServerResponse> {
        return deviceDataService.getRecommendationsFromLatestDeviceData()
            .collectList()
            .flatMap { ok().contentType(MediaType.APPLICATION_JSON).bodyValue(it) }
            .switchIfEmpty(notFound().build())
    }

}
