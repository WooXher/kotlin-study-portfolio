package com.wooxher.portfolio

import org.junit.jupiter.api.Test
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder

@SpringBootTest
class PortfolioApplicationTests {

	@Test
	fun contextLoads() {
	}

	@Test
	fun test(){
		val encrypted = BCryptPasswordEncoder().encode("비밀번호입니다.")
		println(encrypted)
	}
}
