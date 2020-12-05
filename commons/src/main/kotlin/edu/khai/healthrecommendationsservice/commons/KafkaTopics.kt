package edu.khai.healthrecommendationsservice.commons

class KafkaTopics {
    companion object {
        const val DEVICE_DATA = "events_device_data_json_v1"
        const val DEVICE_DATA_AGGREGATED = "events_device_data_aggregated_json_v1"
    }
}