package com.locly.locly.user.application.port.out

import com.locly.locly.user.domain.model.User

interface SaveUserPersistencePort {
    fun save(user: User): User
}
