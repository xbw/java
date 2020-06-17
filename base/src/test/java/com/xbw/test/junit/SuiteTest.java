package com.xbw.test.junit;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/*
 * 测试套件就是组织测试类一起运行的 写一个作为测试套件的入口类，这个类里不需要包含其他的方法
 * 
 * 1.更改测试运行器Suite.class
 * 
 * 2.将要测试的类作为数组传入到Suite.SuiteClasses({})
 */
@RunWith(Suite.class) // 声明套件运行器
@SuiteClasses({ JUnit3Test.class, JUnit4Test.class, JUnit5Test.class }) // 将需要一起测试的类放进来
public class SuiteTest {

}