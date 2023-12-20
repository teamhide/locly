package com.locly.locly.location.application.exception

import com.locly.locly.common.exception.CustomException
import org.springframework.http.HttpStatus

class UserIsNotFriendException : CustomException(
    statusCode = HttpStatus.BAD_REQUEST,
    errorCode = "LOCATION__USER_IS_NOT_A_FRIEND",
    message = "user is not a friend",
)
