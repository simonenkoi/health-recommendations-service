package edu.khai.healthrecommendationsservice.config.locale

import org.springframework.context.annotation.Configuration
import org.springframework.web.reactive.config.DelegatingWebFluxConfiguration
import org.springframework.web.server.i18n.LocaleContextResolver

@Configuration
class LocaleSupportConfig : DelegatingWebFluxConfiguration() {

    override fun createLocaleContextResolver(): LocaleContextResolver {
        return RequestParamLocaleContextResolver()
    }

}