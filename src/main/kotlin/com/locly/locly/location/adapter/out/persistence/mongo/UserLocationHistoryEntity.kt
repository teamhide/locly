package com.locly.locly.location.adapter.out.persistence.mongo

import com.locly.locly.common.config.database.BaseTimestampEntity
import com.locly.locly.location.domain.vo.UserLocation
import jakarta.persistence.Id
import org.springframework.data.mongodb.core.mapping.Document
import org.springframework.data.mongodb.core.mapping.Field
import java.time.LocalDateTime

@Document(collection = "user_location_history")
class UserLocationHistoryEntity(
    @Field(name = "user_id")
    val userId: Long,

    @Field(name = "location")
    val location: UserLocation,

    @Field(name = "stayed_at")
    val stayedAt: LocalDateTime,

    @Id
    val id: String? = null,
) : BaseTimestampEntity()
