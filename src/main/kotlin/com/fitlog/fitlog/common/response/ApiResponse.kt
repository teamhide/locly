package com.fitlog.fitlog.common.response

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity

class ApiResponse<T> : ResponseEntity<T> {
    constructor(httpStatus: HttpStatus) : super(httpStatus)

    constructor(body: T, httpStatus: HttpStatus) : super(body, httpStatus)

    companion object {
        fun success(httpStatus: HttpStatus): ApiResponse<Void> {
            return ApiResponse(httpStatus = httpStatus)
        }

        fun <T> success(body: T, httpStatus: HttpStatus): ApiResponse<T> {
            return ApiResponse(body = body, httpStatus = httpStatus)
        }

        fun fail(httpStatus: HttpStatus): ApiResponse<Void> {
            return ApiResponse(httpStatus = httpStatus)
        }

        fun fail(body: FailBody, httpStatus: HttpStatus): ApiResponse<FailBody> {
            return ApiResponse(body = body, httpStatus = httpStatus)
        }
    }
}
