
package com.xbw.test.junit;

import org.junit.*;

import static org.junit.Assert.assertEquals;

public class JUnit4Test {
//    private Calculator calculator = null;
	private Calculator calculator = new Calculator();

	@Test
	public void add() {
//        Calculator calculator = new Calculator();
		int result = calculator.add(1, 2);
		// 判断方法的返回结果
		assertEquals(3, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	public void subtract() {
//        Calculator calculator = new Calculator();
		int result = calculator.subtract(1, 2);
		// 判断方法的返回结果
		Assert.assertEquals(-1, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	public void multiply() {
//        Calculator calculator = new Calculator();
		int result = calculator.multiply(2, 3);
		// 判断方法的返回结果
		Assert.assertEquals(6, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test
	public void divide() {
//        Calculator calculator = new Calculator();
		int result = calculator.divide(12, 3);
		// 判断方法的返回结果
		Assert.assertEquals(4, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	@Test(expected = ArithmeticException.class)
	public void divideFail() {
//        Calculator calculator = new Calculator();
		calculator.divide(6, 0);
	}

	@Test(expected = Exception.class)
	public void exception() throws Exception {
//        Calculator calculator = new Calculator();
		calculator.exception();
	}

	@Test
	@Ignore
	public void skipped() {
		// not executed
	}

	// @BeforeClass和@AfterClass都需要被 static 修饰
	@BeforeClass
	public static void beforeClass() {
		System.out.println("@BeforeClass");
	}

	@AfterClass
	public static void afterClass() {
		System.out.println("@AfterClass");
	}

	/**
	 * 执行每一个测试方法的时候，先执行before()；
	 */
	@Before
	public void before() {
		System.out.println("@Before");
		// 生成成员变量的实例
//        calculator = new Calculator();
		System.out.println(calculator);// 不管 before() 中有没有new操作，都会产生独立的对象
	}

	/**
	 * 执行每一个测试方法之后，执行after();
	 */
	@After
	public void after() {
		System.out.println("@After");
	}
}