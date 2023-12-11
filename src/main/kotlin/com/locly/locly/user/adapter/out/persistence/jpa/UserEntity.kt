package com.locly.locly.user.adapter.out.persistence.jpa

import com.locly.locly.common.config.database.BaseTimestampEntity
import com.locly.locly.user.domain.vo.UserStatus
import jakarta.persistence.Column
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import java.time.LocalDateTime

class UserEntity(
    @Column(name = "password", nullable = false)
    val password: String,

    @Column(name = "email", nullable = false)
    val email: String,

    @Column(name = "nickname", nullable = false, length = 20)
    val nickname: String,

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    val status: UserStatus,

    @Column(name = "lat", nullable = false)
    val lat: Double,

    @Column(name = "lng", nullable = false)
    val lng: Double,

    @Column(name = "stayed_at", nullable = false)
    val stayedAt: LocalDateTime,

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long = 0L,
) : BaseTimestampEntity()
