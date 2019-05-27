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
import org.springframework.web.reactive.config.ViewResolverRegistry
import org.springframework.web.reactive.config.WebFluxConfigurer
import org.thymeleaf.spring5.ISpringWebFluxTemplateEngine
import org.thymeleaf.spring5.SpringWebFluxTemplateEngine
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver
import org.thymeleaf.spring5.view.reactive.ThymeleafReactiveViewResolver
import org.thymeleaf.templatemode.TemplateMode
import org.thymeleaf.templateresolver.ITemplateResolver
import java.util.*

@Configuration
@EnableWebFlux
class WebConfig : ApplicationContextAware, WebFluxConfigurer {

    private lateinit var context: ApplicationContext

    override fun setApplicationContext(context: ApplicationContext) {
        this.context = context
    }

    @Bean
    fun thymeleafTemplateResolver(): ITemplateResolver {
        val resolver = SpringResourceTemplateResolver()
        resolver.setApplicationContext(context)
        resolver.prefix = "classpath:views/"
        resolver.suffix = ".html"
        resolver.templateMode = TemplateMode.HTML
        resolver.isCacheable = false
        resolver.checkExistence = false
        return resolver
    }

    @Bean
    fun thymeleafTemplateEngine(): ISpringWebFluxTemplateEngine {
        val templateEngine = SpringWebFluxTemplateEngine()
        templateEngine.setTemplateResolver(thymeleafTemplateResolver())
        return templateEngine
    }

    @Bean
    fun thymeleafReactiveViewResolver(): ThymeleafReactiveViewResolver {
        val viewResolver = ThymeleafReactiveViewResolver()
        viewResolver.templateEngine = thymeleafTemplateEngine()
        return viewResolver
    }

    override fun configureViewResolvers(registry: ViewResolverRegistry) {
        registry.viewResolver(thymeleafReactiveViewResolver())
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
