package edu.khai.healthrecommendationsservice.devicedataservice.repository

import edu.khai.healthrecommendationsservice.api.Metrics

interface DeviceDataRepository {

    fun findByKey(key: String): Metrics
}