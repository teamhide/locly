package com.locly.locly.user.adapter.out.persistence

import com.locly.locly.user.adapter.out.persistence.jpa.UserRepository
import com.locly.locly.user.makeUser
import com.locly.locly.user.makeUserEntity
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify

class UserRepositoryAdapterTest : StringSpec({
    val userRepository = mockk<UserRepository>()
    val repositoryAdapter = UserRepositoryAdapter(userRepository = userRepository)

    "email과 nickname으로 유저를 조회한다" {
        // Given
        val userEntity = makeUserEntity()
        every { userRepository.findByEmailOrNickname(any(), any()) } returns userEntity

        // When
        val sut = repositoryAdapter.findByEmailOrNickname(email = userEntity.email, nickname = userEntity.nickname)!!

        // Then
        sut.id shouldBe userEntity.id
        sut.password shouldBe userEntity.password
        sut.email shouldBe userEntity.email
        sut.nickname shouldBe userEntity.nickname
        sut.status shouldBe userEntity.status
        sut.location.lat shouldBe userEntity.lat
        sut.location.lng shouldBe userEntity.lng
        sut.stayedAt shouldBe userEntity.stayedAt
        verify(exactly = 1) { userRepository.findByEmailOrNickname(any(), any()) }
    }

    "유저를 저장한다" {
        // Given
        val user = makeUser()
        val userEntity = makeUserEntity()
        every { userRepository.save(any()) } returns userEntity

        // When
        val sut = repositoryAdapter.save(user = user)

        // Then
        sut.id shouldBe userEntity.id
        sut.password shouldBe userEntity.password
        sut.email shouldBe userEntity.email
        sut.nickname shouldBe userEntity.nickname
        sut.status shouldBe userEntity.status
        sut.location.lat shouldBe userEntity.lat
        sut.location.lng shouldBe userEntity.lng
        sut.stayedAt shouldBe userEntity.stayedAt
        verify(exactly = 1) { userRepository.save(any()) }
    }
})
