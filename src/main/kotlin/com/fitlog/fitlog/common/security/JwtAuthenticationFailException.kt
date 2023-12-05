package com.fitlog.fitlog.common.security

import org.springframework.security.core.AuthenticationException

class JwtAuthenticationFailException : AuthenticationException("AUTHENTICATION_ERROR")
