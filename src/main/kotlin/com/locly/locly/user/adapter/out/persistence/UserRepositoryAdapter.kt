package com.locly.locly.user.adapter.out.persistence

import com.locly.locly.user.adapter.out.persistence.jpa.UserRepository
import com.locly.locly.user.application.port.out.GetUserPersistencePort
import com.locly.locly.user.application.port.out.SaveUserPersistencePort
import com.locly.locly.user.domain.converter.UserConverter
import com.locly.locly.user.domain.model.User
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val userRepository: UserRepository,
) : GetUserPersistencePort, SaveUserPersistencePort {
    override fun findByEmailOrNickname(email: String, nickname: String): User? {
        val userEntity = userRepository.findByEmailOrNickname(email = email, nickname = nickname) ?: run {
            return null
        }
        return UserConverter.from(user = userEntity)
    }

    override fun save(user: User): User {
        val userEntity = UserConverter.to(user = user)
        val savedUser = userRepository.save(userEntity)
        return UserConverter.from(user = savedUser)
    }
}
