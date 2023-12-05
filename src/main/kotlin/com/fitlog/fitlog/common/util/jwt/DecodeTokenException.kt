package com.fitlog.fitlog.common.util.jwt

import com.fitlog.fitlog.common.exception.CustomException
import org.springframework.http.HttpStatus

class DecodeTokenException : CustomException(
    statusCode = HttpStatus.UNAUTHORIZED,
    errorCode = "AUTH__INVALID_TOKEN",
    message = ""
)
