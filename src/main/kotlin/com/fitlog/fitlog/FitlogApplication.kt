package com.fitlog.fitlog

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class FitlogApplication

fun main(args: Array<String>) {
    runApplication<FitlogApplication>(*args)
}
