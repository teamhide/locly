package com.locly.locly.location.adapter.`in`.rest.v1

import com.locly.locly.test.BaseIntegrationTest
import com.locly.locly.user.USER_ID_1_TOKEN
import com.locly.locly.user.adapter.out.persistence.jpa.UserRepository
import com.locly.locly.user.makeUserEntity
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpHeaders
import org.springframework.test.web.servlet.get

class GetUserLocationV1ControllerTest : BaseIntegrationTest() {
    private val URL = "/api/v1/locations/{userId}"

    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `특정 유저의 위치를 조회한다`() {
        // Given
        val userEntity = makeUserEntity()
        val savedUser = userRepository.save(userEntity)

        // When, Then
        mockMvc.get(URL, savedUser.id) {
            header(HttpHeaders.AUTHORIZATION, "Bearer $USER_ID_1_TOKEN")
        }
            .andExpect {
                status { isOk() }
                jsonPath("userId") { value(savedUser.id) }
                jsonPath("nickname") { value(savedUser.nickname) }
                jsonPath("location.lat") { value(savedUser.lat) }
                jsonPath("location.lng") { value(savedUser.lng) }
                jsonPath("stayedAt") { isNotEmpty() }
            }
    }
}
