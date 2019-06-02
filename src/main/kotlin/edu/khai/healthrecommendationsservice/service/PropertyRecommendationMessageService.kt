package edu.khai.healthrecommendationsservice.service

import org.springframework.context.MessageSource
import org.springframework.stereotype.Service
import java.util.Locale

@Service
class PropertyRecommendationMessageService constructor(private var messageSource: MessageSource) : RecommendationMessageService {

    override fun getMessage(code: String, locale: Locale): String {
        return messageSource.getMessage(code, null, locale)
    }
}