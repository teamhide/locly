package com.locly.locly.user.application.service

import com.locly.locly.user.application.exception.UserNotFoundException
import com.locly.locly.user.application.port.`in`.GetUserQuery
import com.locly.locly.user.application.port.`in`.GetUserUseCase
import com.locly.locly.user.application.port.out.GetUserPersistencePort
import com.locly.locly.user.domain.model.User
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetUserService(
    private val getUserPersistencePort: GetUserPersistencePort,
) : GetUserUseCase {
    override fun execute(query: GetUserQuery): User {
        return getUserPersistencePort.findById(id = query.userId) ?: throw UserNotFoundException()
    }
}
