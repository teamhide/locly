package com.locly.locly.common.util.jwt

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

const val secretKey = "hidehidehidehidehidehidehidehidehide"
const val expiredToken = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDE3NTk5MTAsImV4cCI6MTcwMTc1OTk5NywidXNlcl9pZCI6MX0.a3gyosESbCJ_-adDmkPUUa7hrdx2zQe1xebUV252jb8"
const val token = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MDE3NjQ3NzMsInVzZXJfaWQiOjF9.o2l9ZN4aCGhYMEslxwv6rtc-y7Oi8G_nz9OlfNk7kJk"

class TokenProviderTest : StringSpec({
    val provider = TokenProvider(secretKey = secretKey)

    "토큰을 발행한다" {
        val sut = provider.encrypt(userId = 1)

        sut shouldStartWith "ey"
    }

    "토큰을 복호화한다" {
        val sut = provider.decrypt(token = token)

        sut.userId shouldBe 1
    }

    "유효하지 않은 토큰인 경우 예외가 발생한다" {
        shouldThrow<DecodeTokenException> { provider.decrypt(token = "abc") }
    }

    "만료된 토큰이면 예외가 발생한다" {
        shouldThrow<DecodeTokenException> { provider.decrypt(token = expiredToken) }
    }
})
