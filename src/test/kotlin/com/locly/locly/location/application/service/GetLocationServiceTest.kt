package com.locly.locly.location.application.service

import com.locly.locly.location.application.port.`in`.GetLocationQuery
import com.locly.locly.location.application.port.out.UserExternalPort
import com.locly.locly.user.domain.model.UserWithLocation
import com.locly.locly.user.domain.vo.Location
import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

class GetLocationServiceTest : BehaviorSpec({
    val userExternalPort = mockk<UserExternalPort>()
    val getLocationService = GetLocationService(userExternalPort = userExternalPort)

    Given("유저 어그리거트에게") {
        val query = GetLocationQuery(userId = 1L)
        val userLocation = UserWithLocation(
            userId = 2L,
            nickname = "hide",
            location = Location(lat = 12.12, lng = 37.37),
            stayedAt = LocalDateTime.now(),
        )
        every { userExternalPort.getUserLocation(userId = 1L) } returns userLocation

        When("위치를 조회하면") {
            val sut = getLocationService.execute(query = query)

            Then("유저의 위치가 리턴된다") {
                sut.userId shouldBe userLocation.userId
                sut.nickname shouldBe userLocation.nickname
                sut.location shouldBe userLocation.location
                sut.stayedAt shouldBe userLocation.stayedAt
            }
        }
    }
})
