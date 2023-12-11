package com.locly.locly.user.application.port.out

interface SaveUserFriendPersistencePort {
    fun save(userId: Long, friendUserId: Long)
}
