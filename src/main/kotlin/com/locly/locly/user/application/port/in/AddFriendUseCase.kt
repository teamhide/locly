package com.locly.locly.user.application.port.`in`

data class AddFriendCommand(val userId: Long, val friendUserId: Long)

interface AddFriendUseCase {
    fun execute(command: AddFriendCommand)
}
