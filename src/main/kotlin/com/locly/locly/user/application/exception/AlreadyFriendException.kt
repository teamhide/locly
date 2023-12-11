package com.locly.locly.user.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class AlreadyFriendException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST, errorCode = "USER__ALREADY_FRIEND", message = "already friend"
)
