package com.locly.locly.user.application.port.out

interface GetUserFriendPersistencePort {
    fun existsByUserIdAndFriendUserId(userId: Long, friendUserId: Long): Boolean

    fun countsByUserIdLessThan(userId: Long, count: Int): Boolean
}
