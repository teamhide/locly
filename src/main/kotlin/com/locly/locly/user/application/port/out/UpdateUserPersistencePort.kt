package com.locly.locly.user.application.port.out

import com.locly.locly.user.domain.vo.Location

interface UpdateUserPersistencePort {
    fun updateLocationById(userId: Long, location: Location): Long
}
