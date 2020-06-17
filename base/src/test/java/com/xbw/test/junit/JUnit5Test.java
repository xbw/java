
package com.xbw.test.junit;

import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JUnit5Test {
	// private Calculator calculator = null;
	private Calculator calculator = new Calculator();

	@Test
	void add() {
//        Calculator calculator = new Calculator();
		int result = calculator.add(1, 2);
		// 判断方法的返回结果
		assertEquals(3, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	void subtract() {
//        Calculator calculator = new Calculator();
		int result = calculator.subtract(1, 2);
		// 判断方法的返回结果
		Assertions.assertEquals(-1, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	void multiply() {
//        Calculator calculator = new Calculator();
		int result = calculator.multiply(2, 3);
		// 判断方法的返回结果
		Assertions.assertEquals(6, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	void divide() {
//        Calculator calculator = new Calculator();
		int result = calculator.divide(12, 3);
		// 判断方法的返回结果
		Assertions.assertEquals(4, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	void divideFail() {
//        Calculator calculator = new Calculator();
		Assertions.assertThrows(
				// 扔出断言异常
				ArithmeticException.class, () -> calculator.divide(6, 0));
	}

	@Test
	void exception() throws Exception {
//        Calculator calculator = new Calculator();
		Assertions.assertThrows(
				// 扔出断言异常
				Exception.class, () -> calculator.exception());
	}

	@Test
	@Disabled("skipped")
	void skipped() {
		// not executed
	}

	// @BeforeAll和@AfterAll都需要被 static 修饰
	@BeforeAll
	static void beforeAll() {
		System.out.println("@BeforeAll");
	}

	@AfterAll
	static void afterAll() {
		System.out.println("@AfterAll");
	}

	/**
	 * 执行每一个测试方法的时候，先执行beforeEach()；
	 */
	@BeforeEach
	void beforeEach() {
		System.out.println("@BeforeEach");
		// 生成成员变量的实例
//        calculator = new Calculator();
		System.out.println(calculator);// 不管 setUp 中有没有new操作，都会产生独立的对象
	}

	/**
	 * 执行每一个测试方法之后，执行afterEach();
	 */
	@AfterEach
	void afterEach() {
		System.out.println("@AfterEach");
	}
}