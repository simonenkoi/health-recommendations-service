package edu.khai.healthrecommendationsservice.devicedataservice.repository

import edu.khai.healthrecommendationsservice.api.Metrics
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore
import org.springframework.stereotype.Repository

@Repository
class KTableDeviceDataRepository(val table: ReadOnlyKeyValueStore<String, Metrics>) : DeviceDataRepository {

    override fun findByKey(key: String): Metrics = table.get(key)

}