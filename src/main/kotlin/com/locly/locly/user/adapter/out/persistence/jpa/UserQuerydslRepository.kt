package com.locly.locly.user.adapter.out.persistence.jpa

import com.locly.locly.user.domain.vo.UserStatus

interface UserQuerydslRepository {
    fun updateLocationById(userId: Long, lat: Double, lng: Double): Long

    fun updateStatusById(userId: Long, status: UserStatus): Long
}
