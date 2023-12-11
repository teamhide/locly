package com.locly.locly.user.domain.converter

import com.locly.locly.user.makeUser
import com.locly.locly.user.makeUserEntity
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

internal class UserConverterTest : StringSpec({
    "entity -> domain 변환" {
        // Given
        val user = makeUserEntity()

        // When
        val sut = UserConverter.from(user = user)

        // Then
        sut.id shouldBe user.id
        sut.password shouldBe user.password
        sut.email shouldBe user.email
        sut.nickname shouldBe user.nickname
        sut.status shouldBe user.status
        sut.location.lat shouldBe user.lat
        sut.location.lng shouldBe user.lng
        sut.stayedAt shouldBe user.stayedAt
    }

    "domain -> entity 변환" {
        // Given
        val user = makeUser()

        // When
        val sut = UserConverter.to(user = user)

        // Then
        sut.id shouldBe user.id
        sut.password shouldBe user.password
        sut.email shouldBe user.email
        sut.nickname shouldBe user.nickname
        sut.status shouldBe user.status
        sut.lat shouldBe user.location.lat
        sut.lng shouldBe user.location.lng
        sut.stayedAt shouldBe user.stayedAt
    }
})
