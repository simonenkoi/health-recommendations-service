package edu.khai.healthrecommendationsservice.repository

import edu.khai.healthrecommendationsservice.model.RecommendationResult
import org.springframework.data.repository.reactive.ReactiveCrudRepository

interface RecommendationResultRepository : ReactiveCrudRepository<RecommendationResult, Long>