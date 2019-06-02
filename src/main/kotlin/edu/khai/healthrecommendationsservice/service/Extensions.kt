package edu.khai.healthrecommendationsservice.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import reactor.core.publisher.Mono

inline fun <reified T> T.logger(): Logger {
    return LoggerFactory.getLogger(T::class.java)
}

inline fun <reified T, K> T.logReactiveError(throwable: Throwable): Mono<K> {
    logger().error("Error in reactive pipeline", throwable)
    return Mono.empty()
}