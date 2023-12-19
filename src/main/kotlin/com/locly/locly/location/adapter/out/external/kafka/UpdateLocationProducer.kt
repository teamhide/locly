package com.locly.locly.location.adapter.out.external.kafka

import com.locly.locly.location.domain.model.UpdateUserLocation
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.beans.factory.annotation.Value
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class UpdateLocationProducer(
    private val kafkaTemplate: KafkaTemplate<String, Any>,
    @Value("\${spring.kafka.topic.location-updated}")
    private val topicName: String,
) {

    fun send(key: String, message: UpdateUserLocation) {
        kafkaTemplate.send(topicName, key, message)
            .whenComplete { result, ex ->
                if (ex == null) {
                    logger.info { "Sent message=[$message] with offset=[${result.recordMetadata.offset()}]" }
                } else {
                    logger.error { "Unable to send message=[$message] due to : ${ex.message}" }
                }
            }
    }
}
