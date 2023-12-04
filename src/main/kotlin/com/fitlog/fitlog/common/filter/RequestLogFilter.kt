package com.fitlog.fitlog.common.filter

import io.github.oshai.kotlinlogging.KotlinLogging
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.stereotype.Component
import org.springframework.util.StreamUtils
import org.springframework.util.StringUtils
import org.springframework.web.filter.OncePerRequestFilter
import org.springframework.web.util.ContentCachingRequestWrapper
import org.springframework.web.util.ContentCachingResponseWrapper
import java.io.ByteArrayInputStream
import java.lang.Exception
import java.nio.charset.StandardCharsets

private val logger = KotlinLogging.logger { }

@Component
class RequestLogFilter : OncePerRequestFilter() {
    override fun doFilterInternal(request: HttpServletRequest, response: HttpServletResponse, filterChain: FilterChain) {
        val cachedRequest = ContentCachingRequestWrapper(request)
        val cachedResponse = ContentCachingResponseWrapper(response)

        filterChain.doFilter(cachedRequest, cachedResponse)
        logRequest(cachedRequest)
        logResponse(cachedRequest, cachedResponse)
    }

    companion object {
        fun logRequest(request: ContentCachingRequestWrapper) {
            val method = request.method
            val requestURI = request.requestURI
            val queryString = request.queryString
            val formattedQueryString = if (StringUtils.hasText(queryString)) {
                "?$queryString"
            } else {
                ""
            }
            val requestBody = getRequestBody(request)

            if (requestBody.isEmpty()) {
                logger.info { "Request | $method $requestURI$formattedQueryString" }
            } else {
                logger.info { "Request | $method $requestURI$formattedQueryString | body = $requestBody" }
            }
        }

        fun logResponse(request: ContentCachingRequestWrapper, response: ContentCachingResponseWrapper) {
            val method = request.method
            val requestURI = request.requestURI
            val queryString = request.queryString
            val formattedQueryString = if (StringUtils.hasText(queryString)) {
                "?$queryString"
            } else {
                ""
            }
            val statusCode = response.status
            val responseBody = getResponseBody(response)

            if (statusCode < 500) {
                if (responseBody.isEmpty()) {
                    logger.info { "Response | $method $requestURI$formattedQueryString | $statusCode" }
                } else {
                    logger.info { "Response | $method $requestURI$formattedQueryString | $statusCode | body = $responseBody" }
                }
            } else {
                if (responseBody.isEmpty()) {
                    logger.error { "Response | $method $requestURI$formattedQueryString | $statusCode" }
                } else {
                    logger.error { "Response | $method $requestURI$formattedQueryString | $statusCode | body = $responseBody" }
                }
            }
        }

        fun getRequestBody(request: ContentCachingRequestWrapper): String {
            return try {
                val inputStream = ByteArrayInputStream(request.contentAsByteArray)
                StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
            } catch (e: Exception) {
                ""
            }
        }

        fun getResponseBody(response: ContentCachingResponseWrapper): String {
            return try {
                val inputStream = response.contentInputStream
                val responseBody = StreamUtils.copyToString(inputStream, StandardCharsets.UTF_8)
                response.copyBodyToResponse()
                responseBody
            } catch (e: Exception) {
                ""
            }
        }
    }
}
