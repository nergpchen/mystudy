package com.mystudy.example.spring.aop.advice;

import java.lang.reflect.Method;

import org.springframework.aop.ThrowsAdvice;

public class BaseAfterThrowsAdvice implements ThrowsAdvice {

	   public void afterThrowing(Method method, Object[] args, Object target, Throwable throwable) {
	        System.out.println("删除出错啦");
	    }
}
