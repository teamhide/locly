package com.locly.locly.location.application.service

import com.locly.locly.location.application.port.`in`.SaveUserLocationHistoryCommand
import com.locly.locly.location.application.port.`in`.SaveUserLocationHistoryUseCase
import com.locly.locly.location.application.port.out.SaveLocationHistoryPersistencePort
import com.locly.locly.location.domain.model.UserLocationHistory
import org.springframework.stereotype.Service

@Service
class SaveUserLocationHistoryService(
    private val persistencePort: SaveLocationHistoryPersistencePort,
) : SaveUserLocationHistoryUseCase {
    override fun execute(command: SaveUserLocationHistoryCommand) {
        val history = command.let {
            UserLocationHistory(
                userId = it.userId,
                location = it.location,
            )
        }
        persistencePort.save(history = history)
    }
}
