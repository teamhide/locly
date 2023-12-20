package com.locly.locly.common.config.websocket

import com.fasterxml.jackson.databind.ObjectMapper
import com.locly.locly.location.adapter.out.persistence.mongo.UserLocationHistoryRepository
import com.locly.locly.location.domain.model.UpdateUserLocation
import com.locly.locly.location.domain.vo.LocationRequestType
import com.locly.locly.location.makeUserLocationHistoryEntity
import com.locly.locly.user.USER_ID_1_TOKEN
import com.locly.locly.user.adapter.out.persistence.jpa.UserRepository
import com.ninjasquad.springmockk.MockkBean
import io.mockk.every
import io.mockk.verify
import org.awaitility.kotlin.await
import org.awaitility.kotlin.untilCallTo
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.boot.test.web.server.LocalServerPort
import org.springframework.http.HttpHeaders
import org.springframework.web.socket.TextMessage
import org.springframework.web.socket.WebSocketHttpHeaders
import org.springframework.web.socket.client.standard.StandardWebSocketClient
import org.springframework.web.socket.handler.TextWebSocketHandler
import java.net.URI

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WebSocketConfigTest {
    @LocalServerPort
    private var port: Int = 0

    @Autowired
    lateinit var objectMapper: ObjectMapper

    @MockkBean
    lateinit var userRepository: UserRepository

    @MockkBean
    lateinit var userLocationHistoryRepository: UserLocationHistoryRepository

    @Test
    fun `사용자의 위치를 업데이트한다`() {
        // Given
        val webSocketClient = StandardWebSocketClient()
        val handler = TextWebSocketHandler()
        val headers = WebSocketHttpHeaders().apply {
            add(HttpHeaders.AUTHORIZATION, "Bearer $USER_ID_1_TOKEN")
        }
        val uri = URI("ws://localhost:$port/update-location")
        val updatedLocation = UpdateUserLocation(
            type = LocationRequestType.UPDATE,
            userId = 1L,
            lat = 37.3737,
            lng = 17.1717,
        )

        every { userRepository.updateLocationById(any(), any(), any()) } returns 1L

        val userLocationHistoryEntity = makeUserLocationHistoryEntity()
        every { userLocationHistoryRepository.save(any()) } returns userLocationHistoryEntity

        // When
        val session = webSocketClient.execute(handler, headers, uri).get()
        val message = TextMessage(objectMapper.writeValueAsString(updatedLocation))
        session.sendMessage(message)
        session.close()

        // Then
        await untilCallTo {
            verify(exactly = 1) {
                userRepository.updateLocationById(
                    userId = updatedLocation.userId, lat = updatedLocation.lat, lng = updatedLocation.lng,
                )
            }
            verify(exactly = 1) { userLocationHistoryRepository.save(any()) }
        }
    }
}
