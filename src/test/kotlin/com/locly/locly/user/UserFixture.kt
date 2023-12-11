package com.locly.locly.user

import com.locly.locly.user.adapter.out.persistence.jpa.UserEntity
import com.locly.locly.user.domain.model.User
import com.locly.locly.user.domain.vo.Location
import com.locly.locly.user.domain.vo.UserStatus
import java.time.LocalDateTime

fun makeUserEntity(
    password: String = "password",
    email: String = "email",
    nickname: String = "nickname",
    status: UserStatus = UserStatus.ONLINE,
    lat: Double = 37.1234,
    lng: Double = 127.1234,
    stayedAt: LocalDateTime = LocalDateTime.now(),
    id: Long = 1L,
): UserEntity {
    return UserEntity(
        password = password,
        email = email,
        nickname = nickname,
        status = status,
        lat = lat,
        lng = lng,
        stayedAt = stayedAt,
        id = id,
    )
}

fun makeUser(
    password: String = "password",
    email: String = "email",
    nickname: String = "nickname",
    status: UserStatus = UserStatus.ONLINE,
    location: Location = Location(lat = 37.1234, lng = 127.1234),
    stayedAt: LocalDateTime = LocalDateTime.now(),
    id: Long = 1L,
): User {
    return User(
        password = password,
        email = email,
        nickname = nickname,
        status = status,
        location = location,
        stayedAt = stayedAt,
        id = id,
    )
}
