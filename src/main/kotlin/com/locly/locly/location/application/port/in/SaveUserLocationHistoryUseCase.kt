package com.locly.locly.location.application.port.`in`

import com.locly.locly.location.domain.vo.UserLocation

data class SaveUserLocationHistoryCommand(
    val userId: Long,
    val location: UserLocation,
)
interface SaveUserLocationHistoryUseCase {
    fun execute(command: SaveUserLocationHistoryCommand)
}
