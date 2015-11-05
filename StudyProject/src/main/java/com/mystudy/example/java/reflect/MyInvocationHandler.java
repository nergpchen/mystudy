package com.mystudy.example.java.reflect;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MyInvocationHandler implements InvocationHandler {
	
	MyInterface target;
	MyInvocationHandler(MyInterface target){
		this.target = target;
	}
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		// TODO Auto-generated method stub
		System.out.println("执行代理类的方法");
		return method.invoke(target, args);
	}

}
