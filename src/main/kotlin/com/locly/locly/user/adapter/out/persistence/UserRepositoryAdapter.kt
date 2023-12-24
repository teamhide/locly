package com.locly.locly.user.adapter.out.persistence

import com.locly.locly.user.adapter.out.persistence.jpa.UserRepository
import com.locly.locly.user.application.port.out.GetUserPersistencePort
import com.locly.locly.user.application.port.out.SaveUserPersistencePort
import com.locly.locly.user.application.port.out.UpdateUserPersistencePort
import com.locly.locly.user.domain.converter.UserConverter
import com.locly.locly.user.domain.model.User
import com.locly.locly.user.domain.vo.Location
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Component

@Component
class UserRepositoryAdapter(
    private val userRepository: UserRepository,
) : GetUserPersistencePort, SaveUserPersistencePort, UpdateUserPersistencePort {
    override fun findByEmailOrNickname(email: String, nickname: String): User? {
        val userEntity = userRepository.findByEmailOrNickname(email = email, nickname = nickname) ?: run {
            return null
        }
        return UserConverter.from(user = userEntity)
    }

    override fun findAllByIdIn(userIds: List<Long>): List<User> {
        return userRepository.findAllByIdIn(ids = userIds).map {
            User(
                id = it.id,
                password = it.password,
                email = it.email,
                nickname = it.nickname,
                status = it.status,
                location = Location(lat = it.location.x, lng = it.location.y),
                stayedAt = it.stayedAt,
            )
        }
    }

    override fun findById(id: Long): User? {
        val userEntity = userRepository.findByIdOrNull(id = id) ?: run {
            return null
        }
        return UserConverter.from(user = userEntity)
    }

    override fun save(user: User): User {
        val userEntity = UserConverter.to(user = user)
        val savedUser = userRepository.save(userEntity)
        return UserConverter.from(user = savedUser)
    }

    override fun updateLocationById(userId: Long, location: Location): Long {
        return userRepository.updateLocationById(userId = userId, lat = location.lat, lng = location.lng)
    }
}
