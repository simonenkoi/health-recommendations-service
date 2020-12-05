package edu.khai.healthrecommendationsservice.devicedataaggregator

import edu.khai.healthrecommendationsservice.api.Metrics
import kotlin.math.roundToInt

data class MetricsSample(
    val metricsList: MutableList<Metrics> = mutableListOf()
) {

    fun add(metrics: Metrics): MetricsSample {
        metricsList.add(metrics)
        return this
    }

    fun reduce(): Metrics = Metrics(
        height = metricsList.averageOrNull { it.height },
        weight = metricsList.averageOrNull { it.weight },
        sleepDuration = metricsList.roundedAverageOrNull { it.sleepDuration },
        physicalFrequency = metricsList.roundedAverageOrNull { it.physicalFrequency },
        physicalState = metricsList.roundedAverageOrNull { it.physicalState },
        smoking = metricsList.roundedAverageOrNull { it.smoking },
        alcohol = metricsList.roundedAverageOrNull { it.alcohol },
        sugar = metricsList.roundedAverageOrNull { it.sugar },
        coffee = metricsList.roundedAverageOrNull { it.coffee }
    )
}

private fun <T> Collection<T>.averageOrNull(transform: (T) -> Double?): Double? =
    this.mapNotNull(transform).averageOrNull()

@JvmName("averageOrNullOfDouble")
private fun Collection<Double>.averageOrNull(): Double? = if (this.isEmpty()) null else this.average()

private fun <T> Collection<T>.roundedAverageOrNull(transform: (T) -> Int?): Int? =
    this.mapNotNull(transform).averageOrNull()?.roundToInt()

@JvmName("averageOrNullOfInt")
private fun Collection<Int>.averageOrNull(): Double? = if (this.isEmpty()) null else this.average()
