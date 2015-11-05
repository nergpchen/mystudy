package com.mystudy.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;
import org.springframework.aop.framework.adapter.AfterReturningAdviceInterceptor;

/**
 * 实现一个后置环绕通知
 * @author Administrator
 *
 */
public class AfterAdvice implements AfterReturningAdvice {
     
	/**
	 * 后置通知
	 */
	public void afterReturning(Object returnValue, Method method,
			Object[] args, Object target) throws Throwable {
		// TODO Auto-generated method stub
		 System.out.println("===========进入afterAdvice()============ \n");
		    System.out.println("切入点方法执行完了 \n");
	        System.out.print(args[0] + "在");
	        System.out.print(target + "对象上被");
	        System.out.print(method + "方法删除了");
	        System.out.print("只留下：" + returnValue + "\n\n");
	}

	
}
