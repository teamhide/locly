package com.locly.locly.location

import com.locly.locly.location.adapter.out.persistence.mongo.UserLocationHistoryEntity
import com.locly.locly.location.domain.model.UserLocationHistory
import com.locly.locly.location.domain.vo.UserLocation
import org.bson.types.ObjectId

fun makeUserLocationHistoryEntity(
    id: ObjectId = ObjectId(),
    userId: Long = 1L,
    location: UserLocation = UserLocation(lat = 37.1234, lng = 127.1234),
): UserLocationHistoryEntity {
    return UserLocationHistoryEntity(
        id = id,
        userId = userId,
        location = location,
    )
}

fun makeUserLocationHistory(
    id: ObjectId = ObjectId(),
    userId: Long = 1L,
    location: UserLocation = UserLocation(lat = 37.1234, lng = 127.1234),
): UserLocationHistory {
    return UserLocationHistory(
        id = id,
        userId = userId,
        location = location,
    )
}
