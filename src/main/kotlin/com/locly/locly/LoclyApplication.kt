package com.locly.locly

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class LoclyApplication

fun main(args: Array<String>) {
    runApplication<LoclyApplication>(*args)
}
