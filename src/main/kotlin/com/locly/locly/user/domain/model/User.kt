package com.locly.locly.user.domain.model

import com.locly.locly.user.domain.vo.Location
import com.locly.locly.user.domain.vo.UserStatus
import java.time.LocalDateTime

class User(
    val email: String,
    val nickname: String,
    val status: UserStatus,
    val location: Location,
    val stayedAt: LocalDateTime,
    val id: Long = 0L,
)
