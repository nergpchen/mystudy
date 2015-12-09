package com.mystudy.study.java.base;
/**引用资料：
 1: http://www.importnew.com/9078.html
 2: https://en.wikipedia.org/wiki/Reflection_(computer_programming)
 3: http://docs.oracle.com/javase/tutorial/reflect/
 4: http://www.cnblogs.com/jqyp/archive/2012/03/29/2423112.html
 * 学习java反射知识
 * 1：什么是反射?java的反射如何实现
 * 2：作用是什么？
 * 3： 目的是什么?
 * 4: 可以用在哪些场合？
 * 5： 框架结构？接口结构？
 * 6：核心类有哪些？这些类是干嘛用的？
 * @author Administrator
 *
 */
public class StudyReflect {

	/**
	 * 反射是在程序运行的时候，可以知道某个对象的结构和行为并且可以修改某个对象的结构及其行为.
	 * java也实现了反射机制，通过反射机制，提供了如下功能
	 * 1:我们可以获取Class的内部结构，包括类的包，名字，内部的属性，注解和方法.
	 * 2:并且生成一个对象,或对其域设置，或者调用方法。
	 */
	public void 什么是反射(){
		
	}
	/**
	 *java也实现了反射机制，通过反射机制，提供了如下功能
	 *	 1:我们可以获取Class的内部结构，包括类的包，名字，内部的属性，注解和方法.
	 *	 2:并且生成一个对象,或对其域设置，或者调用方法。
	 * 反射的缺点：
	 *   1：性能浪费：反射机制是动态获取的，所以虚拟机优化机制不会生效，所以性能上回低于未反射机制
	 *   2：安全限制：Reflection requires a runtime permission which may not be present when running under a security manager. This is in an important consideration for code which has to run in a restricted security context, such as in an Applet.
	 *   3：内部暴露：由于反射允许代码执行一些在正常情况下不被允许的操作（比如访问私有的属性和方法），所以使用反射可能会导致意料之外的副作用－－代码有功能上的错误，降低可移植性。反射代码破坏了抽象性，因此当平台发生改变的时候，代码的行为就有可能也随着变化。
	 * 框架结构：
	 *   java在java.lang.reflect包中提供了反射的功能.大概有20多个类。
	 *   重要的类有以下几个:
	 *   1:Class:Instances of the class {@code Class} represent classes and interfaces in a running Java applicatio
	 *       Enum枚举类是个Class对象，而注解是个接口类型
	 *   2：Field  :描述一个对象或者接口的属性
	 *   3：Method :描述一个对象的方法
	 *   4：Package :描述一个对象的方法
	 *   3：AnnotatedElement :描述一个对象的方法
	 *   
	 */
	public void java反射介绍(){
		
	}
	
	
}
