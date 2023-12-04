package com.fitlog.fitlog.common.response

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import org.springframework.http.HttpStatus

class ApiResponseTest : StringSpec({
    "HttpStatus만 존재하는 success" {
        val httpStatus = HttpStatus.OK

        val sut = ApiResponse.success(httpStatus)

        sut.body shouldBe null
        sut.statusCode shouldBe httpStatus
    }

    "HttpStatus와 body모두 존재하는 success" {
        val httpStatus = HttpStatus.NOT_FOUND
        val body = "body"

        val sut = ApiResponse.success(body, httpStatus)

        sut.body shouldBe body
        sut.statusCode shouldBe httpStatus
    }

    "HttpStatus만 존재하는 fail" {
        val httpStatus = HttpStatus.BAD_REQUEST

        val sut = ApiResponse.fail(httpStatus)

        sut.body shouldBe null
        sut.statusCode shouldBe httpStatus
    }

    "HttpStatus와 body모두 존재하는 fail" {
        val httpStatus = HttpStatus.BAD_REQUEST
        val body = FailBody(errorCode = "body")

        val sut = ApiResponse.fail(body = body, httpStatus = httpStatus)

        sut.body shouldBe body
        sut.body?.errorCode shouldBe body.errorCode
        sut.statusCode shouldBe httpStatus
    }
})
