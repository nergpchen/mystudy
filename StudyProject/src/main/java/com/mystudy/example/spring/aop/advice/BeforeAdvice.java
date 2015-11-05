package com.mystudy.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.MethodBeforeAdvice;
/**
 * 前置通知
 * @author Administrator
 *
 */
public class BeforeAdvice implements MethodBeforeAdvice{

	//前置通知
	public void before(Method method, Object[] args, Object target)
			throws Throwable {
		// TODO Auto-generated method stub
		 System.out.println("===========进入beforeAdvice()============ \n");		 
	}

	
}
