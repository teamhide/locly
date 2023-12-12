package com.locly.locly.user.adapter.out.persistence.jpa

import com.locly.locly.test.RepositoryTest
import com.locly.locly.user.makeUserEntity
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.repository.findByIdOrNull

@RepositoryTest
class UserQuerydslRepositoryImplTest {
    @Autowired
    lateinit var userRepository: UserRepository

    @Test
    fun `유저의 lat, lng를 업데이트한다`() {
        // Given
        val user = makeUserEntity()
        val savedUser = userRepository.save(user)
        val lat = 40.5555
        val lng = 120.1111

        // When
        val count = userRepository.updateLocationById(userId = savedUser.id, lat = lat, lng = lng)

        // Then
        count shouldBe 1
        val sut = userRepository.findByIdOrNull(savedUser.id)!!
        sut.id shouldBe savedUser.id
        sut.password shouldBe savedUser.password
        sut.email shouldBe savedUser.email
        sut.nickname shouldBe savedUser.nickname
        sut.status shouldBe savedUser.status
        sut.lat shouldBe lat
        sut.lng shouldBe lng
        sut.stayedAt shouldBe savedUser.stayedAt
    }
}
