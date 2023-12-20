package com.locly.locly.location.adapter.out.external

import com.locly.locly.location.domain.vo.UserLocation
import com.locly.locly.user.application.port.`in`.GetFriendLocationsUseCase
import com.locly.locly.user.application.port.`in`.GetUserLocationUseCase
import com.locly.locly.user.application.port.`in`.UpdateUserLocationUseCase
import com.locly.locly.user.domain.model.UserWithLocation
import com.locly.locly.user.domain.vo.Location
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import java.time.LocalDateTime

internal class UserExternalAdapterTest : StringSpec({
    val getFriendLocationsUseCase = mockk<GetFriendLocationsUseCase>()
    val updateUserLocationUseCase = mockk<UpdateUserLocationUseCase>()
    val getUserLocationUseCase = mockk<GetUserLocationUseCase>()
    val externalAdapter = UserExternalAdapter(
        getFriendLocationsUseCase = getFriendLocationsUseCase,
        updateUserLocationUseCase = updateUserLocationUseCase,
        getUserLocationUseCase = getUserLocationUseCase,
    )

    "특정 유저의 친구들 위치를 조회한다" {
        // Given
        val userId = 1L
        val userWithLocation = UserWithLocation(
            userId = 1L, nickname = "hide", location = Location(lat = 37.123, lng = 127.123), stayedAt = LocalDateTime.now()
        )
        every { getFriendLocationsUseCase.execute(any()) } returns arrayListOf(userWithLocation)

        // When
        val sut = externalAdapter.getFriendLocations(userId = userId)

        // Then
        sut.size shouldBe 1
        val location = sut[0]
        location.userId shouldBe userWithLocation.userId
        location.nickname shouldBe userWithLocation.nickname
        location.location shouldBe userWithLocation.location
        location.stayedAt shouldBe userWithLocation.stayedAt
    }

    "id를 기반으로 유저의 위치를 업데이트한다" {
        // Given
        val userId = 1L
        val lat = 10.1234
        val lng = 10.1211
        every { updateUserLocationUseCase.execute(any()) } returns 1L

        // When
        val count = externalAdapter.updateUserLocation(userId = userId, location = UserLocation(lat = lat, lng = lng))

        // Then
        count shouldBe 1
    }

    "id에 해당하는 유저의 위치를 조회한다" {
        // Given
        val userId = 1L
        val userWithLocation = UserWithLocation(
            userId = 1L, nickname = "hide", location = Location(lat = 37.123, lng = 127.123), stayedAt = LocalDateTime.now()
        )
        every { getUserLocationUseCase.execute(any()) } returns userWithLocation

        // When
        val sut = externalAdapter.getUserLocation(userId = userId)

        // Then
        sut.userId shouldBe userWithLocation.userId
        sut.nickname shouldBe userWithLocation.nickname
        sut.location shouldBe userWithLocation.location
        sut.stayedAt shouldBe userWithLocation.stayedAt
    }
})
