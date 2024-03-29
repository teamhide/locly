package com.locly.locly.user.application.service

import com.locly.locly.user.application.exception.UserNotFoundException
import com.locly.locly.user.application.port.`in`.GetUserLocationQuery
import com.locly.locly.user.application.port.`in`.GetUserLocationUseCase
import com.locly.locly.user.application.port.out.GetUserPersistencePort
import com.locly.locly.user.domain.model.UserWithLocation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetUserLocationService(
    private val getUserPersistencePort: GetUserPersistencePort,
) : GetUserLocationUseCase {
    override fun execute(query: GetUserLocationQuery): UserWithLocation {
        val user = getUserPersistencePort.findById(id = query.userId) ?: throw UserNotFoundException()
        return user.let {
            UserWithLocation(
                userId = it.id,
                nickname = it.nickname,
                location = it.location,
                stayedAt = it.stayedAt,
            )
        }
    }
}
