package com.locly.locly.location.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.locly.locly.location.application.port.`in`.UpdateLocationCommand
import com.locly.locly.location.application.port.`in`.UpdateLocationUseCase
import com.locly.locly.location.domain.model.UpdateUserLocation
import com.locly.locly.location.domain.vo.LocationRequestType
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.lang.Exception

private val logger = KotlinLogging.logger { }

@Component
class UpdateLocationWebSocketHandler(
    private val updateLocationUseCase: UpdateLocationUseCase,
    private val objectMapper: ObjectMapper,
) : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val request: UpdateUserLocation
        try {
            request = objectMapper.readValue(message.asBytes(), UpdateUserLocation::class.java)
        } catch (e: Exception) {
            logger.error { "UpdateLocationWebSocketHandler | Invalid message" }
            return
        }

        if (request.type != LocationRequestType.UPDATE) {
            logger.error { "UpdateLocationWebSocketHandler | Invalid type=${request.type}" }
            return
        }
        val command = UpdateLocationCommand(userId = request.userId, lat = request.lat, lng = request.lng)
        updateLocationUseCase.execute(command = command)

        // TODO: 카프카 메시지 전송 코드 추가
    }
}
