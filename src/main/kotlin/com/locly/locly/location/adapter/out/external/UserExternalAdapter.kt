package com.locly.locly.location.adapter.out.external

import com.locly.locly.location.application.port.out.UserExternalPort
import com.locly.locly.location.domain.vo.UserLocation
import com.locly.locly.user.application.port.`in`.GetFriendLocationsQuery
import com.locly.locly.user.application.port.`in`.GetFriendLocationsUseCase
import com.locly.locly.user.application.port.`in`.UpdateUserLocationCommand
import com.locly.locly.user.application.port.`in`.UpdateUserLocationUseCase
import com.locly.locly.user.domain.model.UserWithLocation
import org.springframework.stereotype.Component

@Component
class UserExternalAdapter(
    private val getFriendLocationsUseCase: GetFriendLocationsUseCase,
    private val updateUserLocationUseCase: UpdateUserLocationUseCase,
) : UserExternalPort {
    override fun getFriendLocations(userId: Long): List<UserWithLocation> {
        val query = GetFriendLocationsQuery(userId = userId)
        return getFriendLocationsUseCase.execute(query = query)
    }

    override fun updateUserLocation(userId: Long, location: UserLocation): Long {
        val command = UpdateUserLocationCommand(userId = userId, lat = location.lat, lng = location.lng)
        return updateUserLocationUseCase.execute(command = command)
    }
}
