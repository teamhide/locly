package com.locly.locly.user.application.port.out

import com.locly.locly.user.domain.model.User

interface GetUserPersistencePort {
    fun findByEmailOrNickname(email: String, nickname: String): User?

    fun findAllByIdIn(userIds: List<Long>): List<User>

    fun findById(id: Long): User?
}
