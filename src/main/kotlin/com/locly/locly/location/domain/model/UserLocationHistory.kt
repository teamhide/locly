package com.locly.locly.location.domain.model

import com.locly.locly.location.domain.vo.UserLocation

class UserLocationHistory(
    val id: String? = null,
    val userId: Long,
    val location: UserLocation,
)
