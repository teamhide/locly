package com.locly.locly.user.domain.converter

import com.locly.locly.user.adapter.out.persistence.jpa.UserEntity
import com.locly.locly.user.domain.model.User
import com.locly.locly.user.domain.vo.Location

class UserConverter {
    companion object {
        fun from(user: UserEntity): User {
            with(user) {
                return User(
                    id = id,
                    email = email,
                    nickname = nickname,
                    status = status,
                    location = Location(lat = lat, lng = lng),
                    stayedAt = stayedAt,
                )
            }
        }

        fun to(user: User): UserEntity {
            with(user) {
                return UserEntity(
                    id = id,
                    email = email,
                    nickname = nickname,
                    status = status,
                    lat = location.lat,
                    lng = location.lng,
                    stayedAt = stayedAt,
                )
            }
        }
    }
}
