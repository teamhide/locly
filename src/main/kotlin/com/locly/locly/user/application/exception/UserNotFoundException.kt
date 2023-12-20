package com.locly.locly.user.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class UserNotFoundException : CustomException(
    statusCode = HttpStatus.NOT_FOUND, errorCode = "USER__NOT_FOUND", message = "user not found",
)
