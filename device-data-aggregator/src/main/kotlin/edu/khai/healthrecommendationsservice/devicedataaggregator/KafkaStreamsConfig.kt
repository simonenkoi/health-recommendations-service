package edu.khai.healthrecommendationsservice.devicedataaggregator

import edu.khai.healthrecommendationsservice.api.Metrics
import edu.khai.healthrecommendationsservice.commons.KafkaTopics.Companion.DEVICE_DATA
import edu.khai.healthrecommendationsservice.commons.KafkaTopics.Companion.DEVICE_DATA_AGGREGATED
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.common.serialization.Serdes
import org.apache.kafka.streams.KeyValue
import org.apache.kafka.streams.StreamsBuilder
import org.apache.kafka.streams.kstream.Consumed
import org.apache.kafka.streams.kstream.KStream
import org.apache.kafka.streams.kstream.Materialized
import org.apache.kafka.streams.kstream.Produced
import org.apache.kafka.streams.kstream.TimeWindows
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.annotation.EnableKafkaStreams
import org.springframework.kafka.support.serializer.JsonDeserializer
import org.springframework.kafka.support.serializer.JsonSerializer
import java.time.Duration


@Configuration
@EnableKafka
@EnableKafkaStreams
class KafkaStreamsConfig {

    companion object {
        private val WINDOW_DURATION: Duration = Duration.ofMinutes(1)
    }

    @Bean
    fun aggregatedMetricsStream(kStreamBuilder: StreamsBuilder): KStream<String, Metrics> {
        val stream = kStreamBuilder
            .stream(DEVICE_DATA, Consumed.with(Serdes.String(), metricsSerde()))
        val aggregatedStream = stream.groupByKey()
            .windowedBy(TimeWindows.of(WINDOW_DURATION))
            .aggregate(
                { MetricsSample() },
                { _, value, agg -> agg.add(value) },
                Materialized.with(Serdes.String(), metricsSampleSerde())
            )
            .mapValues(MetricsSample::reduce)
            .toStream()
            .map { key, value -> KeyValue.pair(key.key(), value) }
        aggregatedStream.to(DEVICE_DATA_AGGREGATED, Produced.with(Serdes.String(), metricsSerde()))
        return aggregatedStream
    }

    @Bean
    fun metricsSerde(): Serde<Metrics> {
        return Serdes.serdeFrom(JsonSerializer(), JsonDeserializer(Metrics::class.java))
    }

    @Bean
    fun metricsSampleSerde(): Serde<MetricsSample> {
        return Serdes.serdeFrom(JsonSerializer(), JsonDeserializer(MetricsSample::class.java))
    }

}
