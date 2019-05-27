package edu.khai.healthrecommendationsservice.service

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class PropertyRecommendationService constructor(private var messageSource: MessageSource) : RecommendationService {

    override fun getMessage(code: String, locale: Locale): String {
        return messageSource.getMessage(code, null, locale)
    }
}