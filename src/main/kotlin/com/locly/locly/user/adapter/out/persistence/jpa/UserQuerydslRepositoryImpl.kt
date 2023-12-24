package com.locly.locly.user.adapter.out.persistence.jpa

import com.locly.locly.common.geospatial.PointConverter
import com.querydsl.jpa.impl.JPAQueryFactory
import jakarta.persistence.EntityManager

class UserQuerydslRepositoryImpl(
    private val entityManager: EntityManager,
    private val queryFactory: JPAQueryFactory,
) : UserQuerydslRepository {
    private val userEntity = QUserEntity.userEntity

    override fun updateLocationById(userId: Long, lat: Double, lng: Double): Long {
        val count = queryFactory.update(userEntity)
            .set(userEntity.location, PointConverter.from(lat = lat, lng = lng))
            .where(userEntity.id.eq(userId))
            .execute()
        entityManager.flush()
        entityManager.clear()
        return count
    }
}
