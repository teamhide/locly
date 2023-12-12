package com.locly.locly.user.adapter.out.persistence.jpa

interface UserQuerydslRepository {
    fun updateLocationById(userId: Long, lat: Double, lng: Double): Long
}
