package com.locly.locly.location.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class GhostModeUserException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    errorCode = "LOCATION__USER_IS_GHOST_MODE",
    message = "user is ghost mode",
)
