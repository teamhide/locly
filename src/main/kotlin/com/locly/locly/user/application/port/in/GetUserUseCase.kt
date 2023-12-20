package com.locly.locly.user.application.port.`in`

import com.locly.locly.user.domain.model.User

data class GetUserQuery(val userId: Long)

interface GetUserUseCase {
    fun execute(query: GetUserQuery): User
}
