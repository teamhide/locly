package com.locly.locly.location.adapter.out.external.kafka

import com.locly.locly.location.domain.model.UpdateUserLocation
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.stereotype.Component

@Component
class UpdateLocationConsumer {
    @KafkaListener(
        topics = ["\${spring.kafka.topic.location-updated}"],
        groupId = "hide",
        containerFactory = "kafkaListenerContainerFactory"
    )
    fun listen(data: UpdateUserLocation) {
        println("Received: $data")
    }
}
