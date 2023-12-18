package com.locly.locly.common.config.websocket

import com.locly.locly.location.application.service.GetFriendLocationWebSocketHandler
import com.locly.locly.location.application.service.UpdateLocationWebSocketHandler
import org.springframework.context.annotation.Configuration
import org.springframework.web.socket.config.annotation.EnableWebSocket
import org.springframework.web.socket.config.annotation.WebSocketConfigurer
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry

@Configuration
@EnableWebSocket
class WebSocketConfig(
    private val updateLocationWebSocketHandler: UpdateLocationWebSocketHandler,
    private val getFriendLocationWebSocketHandler: GetFriendLocationWebSocketHandler,
    private val handshakeWithAuthInterceptor: HandshakeWithAuthInterceptor,
) : WebSocketConfigurer {
    override fun registerWebSocketHandlers(registry: WebSocketHandlerRegistry) {
        registry.apply {
            addHandler(updateLocationWebSocketHandler, "/update-location")
                .setAllowedOrigins("*")
                .addInterceptors(handshakeWithAuthInterceptor)
            addHandler(getFriendLocationWebSocketHandler, "/request-location")
                .setAllowedOrigins("*")
                .addInterceptors(handshakeWithAuthInterceptor)
        }
    }
}
