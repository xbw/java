package com.xbw.test.junit;

import junit.framework.Assert;
import junit.framework.TestCase;

import static junit.framework.Assert.assertEquals;

@SuppressWarnings({ "deprecation", "unused" })
public class JUnit3Test extends TestCase {
//  private Calculator calculator = null;
	private Calculator calculator = new Calculator();

	public void testAdd() {
//		Calculator calculator = new Calculator();
		int result = calculator.add(1, 2);
		// 判断方法的返回结果
		assertEquals(3, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	public void testSubtract() {
//		Calculator calculator = new Calculator();
		int result = calculator.subtract(1, 2);
		// 判断方法的返回结果
		Assert.assertEquals(-1, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	public void testMultiply() {
//		Calculator calculator = new Calculator();
		int result = calculator.multiply(2, 3);
		// 判断方法的返回结果
		Assert.assertEquals(6, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	public void testDivide() {
//		Calculator calculator = new Calculator();
		int result = calculator.divide(12, 3);
		// 判断方法的返回结果
		Assert.assertEquals(4, result);// 第一个参数是期望值，第二个参数是要验证的值
	}

	public void testDivideFail() {
//      Calculator calculator = new Calculator();
		Throwable tx = null;
		try {
			calculator.divide(6, 0);
			Assert.fail("没有抛出异常，测试失败");// 如果执行到这行代码，则证明没有抛出异常，说明我们的验证失败
		} catch (Exception e) {
//			e.printStackTrace();
			tx = e;
		}

		Assert.assertEquals(ArithmeticException.class, tx.getClass());// 抛出的异常类型是否和期望一致
		Assert.assertEquals("/ by zero", tx.getMessage());// 抛出的异常信息是否和期望一致
		// 如果上面两个都通过，则测试通过
	}

	public void testException() throws Exception {
//      Calculator calculator = new Calculator();
		Throwable tx = null;
		try {
			calculator.exception();
			Assert.fail("没有抛出异常，测试失败");// 如果执行到这行代码，则证明没有抛出异常，说明我们的验证失败
		} catch (Exception e) {
//			e.printStackTrace();
			tx = e;
		}

		Assert.assertEquals(Exception.class, tx.getClass());// 抛出的异常类型是否和期望一致
		Assert.assertEquals("除数不能为0！", tx.getMessage());// 抛出的异常信息是否和期望一致
		// 如果上面两个都通过，则测试通过
	}

	void skipped() {
		// not executed
	}

	/**
	 * 执行每一个测试方法的时候，先执行setUp()；
	 */
	@Override
	public void setUp() throws Exception {
		System.out.println("setUp");
		// 生成成员变量的实例
//      calculator = new Calculator();
		System.out.println(calculator);// 不管 setUp 中有没有new操作，都会产生独立的对象
	}

	/**
	 * 执行每一个测试方法之后，执行tearDown();
	 */
	@Override
	public void tearDown() throws Exception {
		System.out.println("tearDown");
	}
}
