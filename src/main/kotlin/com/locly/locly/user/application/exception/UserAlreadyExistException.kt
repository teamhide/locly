package com.locly.locly.user.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class UserAlreadyExistException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST, errorCode = "USER__ALREADY_EXIST", message = "user already exist"
)
