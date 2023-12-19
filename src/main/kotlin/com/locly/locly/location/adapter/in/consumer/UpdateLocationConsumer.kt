package com.locly.locly.location.adapter.`in`.consumer

import com.locly.locly.location.application.port.`in`.UpdateLocationCommand
import com.locly.locly.location.application.port.`in`.UpdateLocationUseCase
import com.locly.locly.location.domain.model.UpdateUserLocation
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

private val logger = KotlinLogging.logger { }

@Component
class UpdateLocationConsumer(
    private val updateLocationUseCase: UpdateLocationUseCase,
) {
    @KafkaListener(
        topics = ["\${spring.kafka.topic.location-updated}"],
        groupId = "hide",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun listen(message: UpdateUserLocation) {
        logger.info { "Received: $message" }
        val command = message.let {
            UpdateLocationCommand(
                userId = it.userId,
                lat = it.lat,
                lng = it.lng,
            )
        }
        updateLocationUseCase.execute(command = command)
    }
}
