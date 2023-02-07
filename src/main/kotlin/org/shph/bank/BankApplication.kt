package org.shph.bank

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.scheduling.annotation.EnableScheduling

@SpringBootApplication
@EnableScheduling
class BankApplication

fun main(args: Array<String>) {
    runApplication<BankApplication>(*args)
}
