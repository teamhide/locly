package com.locly.locly.common.config

import org.apache.kafka.clients.admin.AdminClientConfig
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.KafkaAdmin

@Configuration
class KafkaConfig {
    @Value("\${spring.kafka.bootstrap-servers}")
    lateinit var bootstrapServers: String

    @Bean
    fun kafkaAdmin(): KafkaAdmin {
        val config = mapOf(AdminClientConfig.BOOTSTRAP_SERVERS_CONFIG to bootstrapServers)
        return KafkaAdmin(config)
    }
}
