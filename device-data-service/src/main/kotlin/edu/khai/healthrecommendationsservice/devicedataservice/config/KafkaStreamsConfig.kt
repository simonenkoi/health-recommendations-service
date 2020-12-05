package edu.khai.healthrecommendationsservice.devicedataservice.config

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.commons.KafkaTopics
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KafkaStreams
import org.apache.kafka.streams.StoreQueryParameters
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.Topology
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KTable
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.state.QueryableStoreTypes
import org.apache.kafka.streams.state.ReadOnlyKeyValueStore
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.config.StreamsBuilderFactoryBean
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.util.concurrent.CountDownLatch
import java.util.concurrent.TimeUnit


@Configuration
@EnableKafka
@EnableKafkaStreams
class KafkaStreamsConfig(val streamsBuilder: StreamsBuilder) {

    @Bean
    fun deviceDataTable(): KTable<String, Metrics> {
        return streamsBuilder.table(
            KafkaTopics.DEVICE_DATA_AGGREGATED,
            Consumed.with(Serdes.String(), metricsSerde())
                .withOffsetResetPolicy(Topology.AutoOffsetReset.EARLIEST),
            Materialized.`as`("device-data-aggregated")
        )
    }

    @Bean
    fun metricsSerde(): Serde<Metrics> {
        return Serdes.serdeFrom(JsonSerializer(), JsonDeserializer(Metrics::class.java))
    }

    @Bean
    fun latch(streamsBuilderFB: StreamsBuilderFactoryBean): CountDownLatch {
        val latch = CountDownLatch(1)
        streamsBuilderFB.setStateListener { newState, _ ->
            if (newState == KafkaStreams.State.RUNNING) {
                latch.countDown()
            }
        }
        return latch
    }

    @Bean
    fun deviceDataMaterializedTable(
        streamsBuilderFB: StreamsBuilderFactoryBean,
        deviceDataTable: KTable<String, Metrics>
    ): ReadOnlyKeyValueStore<String, Metrics> {
        streamsBuilderFB.start()
        latch(streamsBuilderFB).await(50, TimeUnit.SECONDS)
        return streamsBuilderFB.kafkaStreams.store(
            StoreQueryParameters.fromNameAndType(
                deviceDataTable.queryableStoreName(),
                QueryableStoreTypes.keyValueStore()
            )
        )
    }
}