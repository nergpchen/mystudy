package com.mystudy.example.java.reflect;

import java.lang.reflect.Proxy;

public class MyTarget implements MyInterface{

	public void add() {
		// TODO Auto-generated method stub
		System.out.println("执行add()方法");
	}

	public static void main(String[] args) {
		MyImpl target = new MyImpl();
		MyInterface proxy = (MyInterface)Proxy.newProxyInstance
				(MyTarget.class.getClassLoader(), new Class[]{MyInterface.class}, new MyInvocationHandler(target));
		
		proxy.add();
	}
}
