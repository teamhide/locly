package com.locly.locly.location.application.service

import com.fasterxml.jackson.databind.ObjectMapper
import com.locly.locly.location.application.port.`in`.GetLocationsQuery
import com.locly.locly.location.application.port.`in`.GetLocationsUseCase
import com.locly.locly.location.domain.model.RequestFriendLocation
import com.locly.locly.location.domain.vo.LocationRequestType
import io.github.oshai.kotlinlogging.KotlinLogging
import org.springframework.stereotype.Component
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketSession
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.lang.Exception

private val logger = KotlinLogging.logger { }

@Component
class GetFriendLocationWebSocketHandler(
    private val getLocationsUseCase: GetLocationsUseCase,
    private val objectMapper: ObjectMapper,
) : TextWebSocketHandler() {
    override fun handleTextMessage(session: WebSocketSession, message: TextMessage) {
        val request: RequestFriendLocation
        try {
            request = objectMapper.readValue(message.asBytes(), RequestFriendLocation::class.java)
        } catch (e: Exception) {
            logger.error { "GetFriendLocationWebSocketHandler | Invalid message" }
            return
        }

        if (request.type != LocationRequestType.REQUEST) {
            logger.error { "UpdateLocationWebSocketHandler | Invalid type=${request.type}" }
            return
        }

        val query = GetLocationsQuery(userId = request.userId)
        val locations = getLocationsUseCase.execute(query = query)
        session.sendMessage(TextMessage(objectMapper.writeValueAsString(locations)))
    }
}
