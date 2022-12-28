package br.com.alura.forum

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cache.annotation.EnableCaching

@SpringBootApplication
@EnableCaching
class AluraKotlinSpringApplication

fun main(args: Array<String>) {
	runApplication<AluraKotlinSpringApplication>(*args)
}
