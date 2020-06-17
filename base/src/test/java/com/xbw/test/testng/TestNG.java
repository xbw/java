package com.xbw.test.testng;

import org.testng.Assert;
import static org.testng.Assert.assertEquals;

import org.testng.annotations.*;

/**
 * @BeforeSuite
 * @AfterSuite
 * @BeforeTest
 * @AfterTest
 * @BeforeGroups
 * @AfterGroups
 * @BeforeClass
 * @AfterClass
 * @BeforeMethod
 * @AfterMethod
 * 
 * @author xbw
 * 
 */
public class TestNG {

//  private Calculator calculator = null;
	private Calculator calculator = new Calculator();

	@Test(groups = { "calculator" })
	public void add() {
		assertEquals(3, calculator.add(1, 2)); // 第一个参数是期望值，第二个参数是要验证的值
	}

	@Parameters({ "a", "b" })
	@Test(groups = { "calculator" })
	public void add(int a, int b) {
		Assert.assertEquals(3, calculator.add(a, b)); // 第一个参数是期望值，第二个参数是要验证的值
	}

	@Parameters({ "a", "b" })
	@Test(groups = { "calculator" })
	public void subtract(int a, int b) {
		assert -1 == calculator.subtract(a, b);
	}

	@Parameters({ "b", "c" })
	@Test(groups = { "calculator" })
	public void multiply(int a, int b) {
		assert 6 == calculator.multiply(a, b);
	}

	@Parameters({ "d", "c" })
	@Test(groups = { "calculator" })
	public void divide(int a, int b) {
		assert 4 == calculator.divide(a, b);
	}

	@Test(expectedExceptions = ArithmeticException.class, groups = { "exception" })
	public void divideFail() {
		calculator.divide(6, 0);
	}

	@Test(expectedExceptions = Exception.class, groups = { "exception" })
	public void exception() throws Exception {
		calculator.exception();
	}

	@Test(expectedExceptions = Exception.class, groups = { "exception" })
	public void exclude() throws Exception {
		calculator.exception();
	}
}