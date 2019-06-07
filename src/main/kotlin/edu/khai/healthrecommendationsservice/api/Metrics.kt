package edu.khai.healthrecommendationsservice.api

import javax.validation.constraints.Max
import javax.validation.constraints.Min

data class Metrics(
    @get:Min(0)
    val height: Double? = null,
    @get:Min(0)
    val weight: Double? = null,
    @get:Min(0)
    val sleepDuration: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val physicalFrequency: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val physicalState: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val smoking: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val alcohol: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val sugar: Int? = null,
    @get:Min(0)
    @get:Max(5)
    val coffee: Int? = null
)