package com.locly.locly.location.application.port.`in`

import com.locly.locly.user.domain.model.UserWithLocation

data class GetLocationQuery(val userId: Long, val friendUserId: Long)

interface GetLocationUseCase {
    fun execute(query: GetLocationQuery): UserWithLocation
}
