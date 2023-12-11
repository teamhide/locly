package com.locly.locly.user.adapter.out.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository

interface UserRepository : JpaRepository<UserEntity, Long> {
    fun findByEmailOrNickname(email: String, nickname: String): UserEntity?
}
