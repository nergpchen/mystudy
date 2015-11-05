package com.mystudy.example.spring.aop.pointcut;

import java.lang.reflect.Method;

import org.springframework.aop.support.NameMatchMethodPointcut;

/**
 * 定义切入点:用来过滤哪些方法需要切入,覆盖matches方法实现匹配功能
 * 同时要在applicaionContext文件中定义
 *  <bean id="pointcut" class="com.mystudy.spring.pointcut.MyPointCut" />
 * 
 * @author Administrator
 *
 */
public class MyPointCut extends NameMatchMethodPointcut {

	/**
	 * 定义哪些方法匹配.
	 */
	@SuppressWarnings("rawtypes")
	@Override
	public boolean matches(Method method, Class targetClass) {
		// 设置单个方法匹配
		this.setMappedName("delete");
		// 设置多个方法匹配
		String[] methods = { "delete", "modify" };

		this.setMappedNames(methods);

		return super.matches(method, targetClass);
	}

}
