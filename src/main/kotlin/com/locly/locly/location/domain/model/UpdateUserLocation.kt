package com.locly.locly.location.domain.model

import com.locly.locly.location.domain.vo.LocationRequestType

data class UpdateUserLocation(
    val type: LocationRequestType,
    val userId: Long,
    val lat: Double,
    val lng: Double,
)
