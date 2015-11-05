package com.mystudy.study.spring;

import java.lang.reflect.Proxy;

/**
 * Spring AOP是实现了AOP功能的框架，那么我们需要了解框架，基本知识，怎么使用和原理的实现.
 * 1:AOP框架结构
 * 2:SpingAOp怎么实现的AOP功能的
 * 3:学习如何使用Spring AOP
 * 4:核心类和核心代码的学习
 * Spring AOP框架:
 * 1：Advisor :Advisor是Pointcut和Advice的配置器，它包括Pointcut和Advice，是将Advice注入程序中Pointcut位置的代码
 * 通过Advisor来获取一个通知
 * 2：Advice:通知，Spring定义了
 * BeforeAdvice,
 * AfterAdvice,
 * AfterReturningAdvice,
 * DynamicIntroductionAdvice
 * ThrowsAdvice:异常通知
 * 3:Pointcut:切入点： 
 *  ClassFilter getClassFilter();
 *  MethodMatcher getMethodMatcher();
 *  通过上面的两个方法类匹配和方法匹配来定义切入点
 * 
 * 核心类：
 * ProxyBeanFacotry,JDKProxyInstance
 * ProxyBeanFacotry:是一个代理类Bean工厂
 * JDkProxyInstance :代理类工厂
 * @author Administrator
 *
 */
public class StudySpringAOP {

	/**
	 * 学习SpringAOP的框架和接口
	 * Spign AOP是遵循AOP原理的，所以Spring具有AOP的基本框架,并且做了扩展.
	 */
	public void interfaceOfSpringAOP(){
		
	}
	/**
	 * JDK动态代理：就是在java在程序运行期间根据接口生成代理类和代理对象,每个代理类的对象都会关联一个表示内部处理逻辑的InvocationHandler接口的实现。
	 * 特点：1基于接口的; 2每个代理类都有对应的InvocationHandler来实现内部逻辑处理。
	 * 那么怎么实现呢？涉及到哪些技术呢？
	 * 怎么使用呢？
	 */
	public void studyJDK动态代理(){
		
	}
}
