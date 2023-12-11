package com.locly.locly.common.util.jwt

import com.locly.locly.user.EXPIRED_TOKEN
import com.locly.locly.user.USER_ID_1_TOKEN
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

const val secretKey = "hidehidehidehidehidehidehidehidehide"

class TokenProviderTest : StringSpec({
    val provider = TokenProvider(secretKey = secretKey)

    "토큰을 발행한다" {
        val sut = provider.encrypt(userId = 1)

        sut shouldStartWith "ey"
    }

    "토큰을 복호화한다" {
        val sut = provider.decrypt(token = USER_ID_1_TOKEN)

        sut.userId shouldBe 1
    }

    "유효하지 않은 토큰인 경우 예외가 발생한다" {
        shouldThrow<DecodeTokenException> { provider.decrypt(token = "abc") }
    }

    "만료된 토큰이면 예외가 발생한다" {
        shouldThrow<DecodeTokenException> { provider.decrypt(token = EXPIRED_TOKEN) }
    }
})
