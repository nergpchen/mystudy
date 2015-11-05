package com.mystudy.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

/**
 * 环绕通知
 * @author Administrator
 *
 */
public class AroundAdvice implements MethodInterceptor {

	/**
	 * 环绕通知
	 */
	public Object intercept(Object arg0, Method arg1, Object[] arg2,
			MethodProxy arg3) throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("===========进入AroundAdvice()============ \n");
		return arg1.invoke(arg0, arg1);
		
	}

}
