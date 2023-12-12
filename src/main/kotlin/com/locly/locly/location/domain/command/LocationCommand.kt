package com.locly.locly.location.domain.command

import com.locly.locly.location.domain.vo.LocationRequestType

data class LocationCommand(
    val type: LocationRequestType,
    val userId: Long,
)
