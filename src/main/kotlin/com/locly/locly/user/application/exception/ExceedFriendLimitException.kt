package com.locly.locly.user.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class ExceedFriendLimitException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST, errorCode = "USER__EXCEED_FRIEND_LIMIT", message = "exceed friend limit",
)
