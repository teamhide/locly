package com.locly.locly.location.application.port.out

import com.locly.locly.location.domain.vo.UserLocation
import com.locly.locly.user.domain.model.UserWithLocation

interface UserExternalPort {
    fun getFriendLocations(userId: Long): List<UserWithLocation>

    fun updateUserLocation(userId: Long, location: UserLocation): Long
}
