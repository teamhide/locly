package com.locly.locly.user.domain.model

import com.locly.locly.user.domain.vo.Location
import java.time.LocalDateTime

class UserWithLocation(val userId: Long, val nickname: String, val location: Location, val stayedAt: LocalDateTime)
