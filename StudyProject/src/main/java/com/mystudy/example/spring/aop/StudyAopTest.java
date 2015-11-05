package com.mystudy.example.spring.aop;

import java.lang.reflect.Proxy;

import org.junit.Test;
import org.springframework.aop.support.AopUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class StudyAopTest {

	public static Object getSpringBean(Class<?> requiredType){
		 ApplicationContext context =null;
		 if(context == null){
			context = (ApplicationContext) new ClassPathXmlApplicationContext("applicationContext.xml");
		 }
		 return context.getBean(requiredType);
	}
	@Test
	/**
	 * 测试SpringAOP
	 * 在容器中定义了MyPonintcut来匹配切入点,定义了通知器.
	 * 然后再配置文件中定义好相关的bean:pintcut ,advice等bean
	 * 同时配置好 DefaultPointcutAdvisor容器,
	 * 那么在执行下面方法的时候,如果和定义的切入点匹配
	 * 则会执行相应的通知动作。
	 * 所以，要使用Spring的AOP，通常要定义自己的切入点和通知动作.
	 */
	public void testSpringAOP(){
		BaseBusinessImpl base = (BaseBusinessImpl) StudyAopTest.getSpringBean(BaseBusinessImpl.class);
		base.add();
	}
}
