package com.locly.locly.location.application.port.out

import com.locly.locly.location.domain.model.UserLocationHistory

interface SaveLocationHistoryPersistencePort {
    fun save(history: UserLocationHistory)
}
