package com.locly.locly.location.domain.model

import com.locly.locly.location.domain.vo.UserLocation
import org.bson.types.ObjectId

class UserLocationHistory(
    val id: ObjectId = ObjectId(),
    val userId: Long,
    val location: UserLocation,
)
