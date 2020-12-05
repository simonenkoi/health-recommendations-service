package edu.khai.healthrecommendationsservice.model

import edu.khai.healthrecommendationsservice.api.Metrics
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document
data class RecommendationResult(
    @Id val id: String? = null,
    val metrics: Metrics,
    val recommendation: String
)