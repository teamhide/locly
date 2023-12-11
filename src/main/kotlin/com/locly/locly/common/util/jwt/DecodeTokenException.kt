package com.locly.locly.common.util.jwt

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class DecodeTokenException : CustomException(
    statusCode = HttpStatus.UNAUTHORIZED,
    errorCode = "AUTH__INVALID_TOKEN",
    message = ""
)
