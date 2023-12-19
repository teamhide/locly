package com.locly.locly.location.adapter.out.persistence

import com.locly.locly.location.adapter.out.persistence.mongo.UserLocationHistoryRepository
import com.locly.locly.location.application.port.out.SaveLocationHistoryPersistencePort
import com.locly.locly.location.domain.converter.UserLocationHistoryConverter
import com.locly.locly.location.domain.model.UserLocationHistory
import org.springframework.stereotype.Component

@Component
class UserLocationHistoryRepositoryAdapter(
    private val userLocationHistoryRepository: UserLocationHistoryRepository,
) : SaveLocationHistoryPersistencePort {
    override fun save(history: UserLocationHistory) {
        val historyEntity = UserLocationHistoryConverter.to(history)
        userLocationHistoryRepository.save(historyEntity)
    }
}
