package com.locly.locly.user.application.port.`in`

import com.locly.locly.user.domain.model.UserWithLocation

data class GetFriendLocationsQuery(val userId: Long)

interface GetFriendLocationsUseCase {
    fun execute(query: GetFriendLocationsQuery): List<UserWithLocation>
}
