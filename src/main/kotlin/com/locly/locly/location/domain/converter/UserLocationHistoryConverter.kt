package com.locly.locly.location.domain.converter

import com.locly.locly.location.adapter.out.persistence.mongo.UserLocationHistoryEntity
import com.locly.locly.location.domain.model.UserLocationHistory

class UserLocationHistoryConverter {
    companion object {
        fun from(locationHistory: UserLocationHistoryEntity): UserLocationHistory {
            with(locationHistory) {
                return UserLocationHistory(
                    id = id,
                    location = location,
                    userId = userId,
                    stayedAt = stayedAt,
                )
            }
        }

        fun to(locationHistory: UserLocationHistory): UserLocationHistoryEntity {
            with(locationHistory) {
                return UserLocationHistoryEntity(
                    id = id,
                    location = location,
                    userId = userId,
                    stayedAt = stayedAt,
                )
            }
        }
    }
}
