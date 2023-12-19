package com.locly.locly.location.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.locly.locly.common.config.websocket.HandshakeWithAuthInterceptor
import com.locly.locly.location.application.port.out.MessagingPort
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
    private val objectMapper: ObjectMapper,
    private val messagingPort: MessagingPort,
) : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        if (!HandshakeWithAuthInterceptor.isUserIdExist(session = session)) {
            logger.error { "UpdateLocationWebSocketHandler | Current user id is null" }
            return
        }

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

        val userId = session.attributes[HandshakeWithAuthInterceptor.SESSION_USER_ID_KEY]
        if (userId != request.userId) {
            logger.error { "UpdateLocationWebSocketHandler | Auth error. userId=$userId, requestUserId=${request.userId}" }
        }

        messagingPort.sendLocationUpdated(message = request)
    }
}
