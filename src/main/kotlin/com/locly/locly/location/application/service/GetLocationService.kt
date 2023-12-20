package com.locly.locly.location.application.service

import com.locly.locly.location.application.port.`in`.GetLocationQuery
import com.locly.locly.location.application.port.`in`.GetLocationUseCase
import com.locly.locly.location.application.port.out.UserExternalPort
import com.locly.locly.user.domain.model.UserWithLocation
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional(readOnly = true)
class GetLocationService(
    private val userExternalPort: UserExternalPort,
) : GetLocationUseCase {
    override fun execute(query: GetLocationQuery): UserWithLocation {
        return userExternalPort.getUserLocation(userId = query.userId)
    }
}
