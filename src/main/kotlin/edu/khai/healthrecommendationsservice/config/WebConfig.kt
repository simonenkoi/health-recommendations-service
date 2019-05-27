package edu.khai.healthrecommendationsservice.config

import org.springframework.beans.factory.config.YamlPropertiesFactoryBean
import org.springframework.context.ApplicationContext
import org.springframework.context.ApplicationContextAware
import org.springframework.context.MessageSource
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.support.ResourceBundleMessageSource
import org.springframework.core.io.ClassPathResource
import org.springframework.web.reactive.config.EnableWebFlux
import org.springframework.web.reactive.config.WebFluxConfigurer
import java.util.Properties

@Configuration
@EnableWebFlux
class WebConfig : ApplicationContextAware, WebFluxConfigurer {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(context: ApplicationContext) {
        this.context = context
    }

    @Bean
    fun yamlProperties(): Properties? {
        val bean = YamlPropertiesFactoryBean()
        bean.setResources(ClassPathResource("languages/messages.yml"))
        return bean.getObject()
    }

    @Bean
    fun messageSource(): MessageSource {
        val messageSource = ResourceBundleMessageSource()
        messageSource.setCommonMessages(yamlProperties())
        messageSource.setDefaultEncoding("UTF-8")
        return messageSource
    }
}
