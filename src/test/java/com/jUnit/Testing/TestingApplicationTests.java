package com.jUnit.Testing;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

@Slf4j
class TestingApplicationTests {

	@BeforeEach
	void beforeEach(){
		log.info("Before each method...");
	}

	@AfterEach
	void afterEach(){
		log.info("After each method...");
	}

	@BeforeAll
	static void beforeAll(){
		log.info("Before all method...");
	}

	@AfterAll
	static void afterAll(){
		log.info("After all method...");
	}

	@Test
//	@DisplayName("First Method")
	void first() {
		 int a = 5;
		 int b = 0;

		Assertions.assertThatThrownBy(()->divide(a, b))
				.isInstanceOf(Exception.class)
				.hasMessage("Tried to divide by zero");
	}

	@Test
//	@Disabled
	void second(){
		 Assertions.assertThat("Apple")
				 .isEqualTo("Apple")
				 .startsWith("app");
	}

	int divide(int a, int b){
		try{
			return a/b;
		}catch(ArithmeticException e){
			throw new ArithmeticException("Tried to divide by zero");
		}
	}

}
