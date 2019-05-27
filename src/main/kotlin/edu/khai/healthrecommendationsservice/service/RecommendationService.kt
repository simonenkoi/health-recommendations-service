package edu.khai.healthrecommendationsservice.service

import org.springframework.context.i18n.LocaleContextHolder
import java.util.Locale

interface RecommendationService {

    fun getMessage(code: String, locale: Locale = LocaleContextHolder.getLocale()): String
}