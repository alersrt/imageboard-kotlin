package me.alersrt.imageboard.config

import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import org.springframework.cache.annotation.EnableCaching
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.scheduling.annotation.EnableAsync
import org.springframework.scheduling.annotation.EnableScheduling

@EnableAsync
@EnableCaching
@EnableScheduling
@Configuration
class RootConfig {

    @Bean
    fun javaTimeModuleBean(): JavaTimeModule {
        return JavaTimeModule()
    }

    @Bean
    fun objectMapper(): ObjectMapper {
        val mapper = ObjectMapper()
        mapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        mapper.setSerializationInclusion(JsonInclude.Include.ALWAYS)
        mapper.registerModule(javaTimeModuleBean())
        mapper.registerKotlinModule()
        return mapper
    }
}