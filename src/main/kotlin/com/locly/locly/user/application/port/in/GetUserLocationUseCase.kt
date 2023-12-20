package com.locly.locly.user.application.port.`in`

import com.locly.locly.user.domain.model.UserWithLocation

data class GetUserLocationQuery(val userId: Long)

interface GetUserLocationUseCase {
    fun execute(query: GetUserLocationQuery): UserWithLocation
}
