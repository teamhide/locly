package com.locly.locly.location.application.port.out

import com.locly.locly.location.domain.model.UpdateUserLocation

interface MessagingPort {
    fun sendLocationUpdated(message: UpdateUserLocation)
}
