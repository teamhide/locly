package com.locly.locly.location.application.port.`in`

import com.locly.locly.user.domain.model.UserWithLocation

data class GetLocationsQuery(val userId: Long)
interface GetLocationsUseCase {
    fun execute(query: GetLocationsQuery): List<UserWithLocation>
}
