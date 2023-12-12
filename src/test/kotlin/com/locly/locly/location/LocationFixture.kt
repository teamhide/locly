package com.locly.locly.location

import com.locly.locly.location.adapter.out.persistence.mongo.UserLocationHistoryEntity
import com.locly.locly.location.domain.model.UserLocationHistory
import com.locly.locly.location.domain.vo.UserLocation
import java.time.LocalDateTime

fun makeUserLocationHistoryEntity(
    id: String = "id",
    userId: Long = 1L,
    location: UserLocation = UserLocation(lat = 37.1234, lng = 127.1234),
    stayedAt: LocalDateTime = LocalDateTime.now(),
): UserLocationHistoryEntity {
    return UserLocationHistoryEntity(
        id = id,
        userId = userId,
        location = location,
        stayedAt = stayedAt,
    )
}

fun makeUserLocationHistory(
    id: String = "id",
    userId: Long = 1L,
    location: UserLocation = UserLocation(lat = 37.1234, lng = 127.1234),
    stayedAt: LocalDateTime = LocalDateTime.now(),
): UserLocationHistory {
    return UserLocationHistory(
        id = id,
        userId = userId,
        location = location,
        stayedAt = stayedAt,
    )
}
