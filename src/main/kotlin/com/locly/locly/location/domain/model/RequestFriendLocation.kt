package com.locly.locly.location.domain.model

import com.locly.locly.location.domain.vo.LocationRequestType

data class RequestFriendLocation(val type: LocationRequestType, val userId: Long)
