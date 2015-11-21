package com.mystudy.study.spring;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

/**
 * SpringIOC容器的学习:
 * IOC容器的功能有哪些？
 * SpringIOC容器的使用
 * IOC容器的框架结构?
 * IOC容器的核心接口有哪些？
 * IOC容器的公共接口有哪些？
 * IOC容器的实现类源码分析？
 * SpringIOC容器的设计思想和模式的学习.
 *1:核心接口：
 *BeanFactory:
 *这个是BeanFactory的接口层次图：http://dl2.iteye.com/upload/attachment/0063/3120/0e57dc20-6b26-3217-878e-6bd766833cf4.jpg
 *
 *
 */
public class StudySpringIOC {

	/**
	 *  ApplicationContext context = new FileSystemXmlApplicationContext("applicationContext.xml"); 
	 *  context就是一个IOC的容器.AppliactionContext是一个容器接口,FileSystemXmlApplicationContext是一个容器的实现类.
	 *  
	 */
	public void howTouse(){
		  ApplicationContext context = new FileSystemXmlApplicationContext(   
	                "applicationContext.xml");   
	       
	}
	/**
	 * SpringIOC容器的框架:
	 * http://dl2.iteye.com/upload/attachment/0063/3120/0e57dc20-6b26-3217-878e-6bd766833cf4.jpg
	 * Spring提供了基本的BeanFactory,ApplicationContext和WebAppliactionContext接口.
	 * BeanFactory:提供最基本的Bean容器
	 * ApplicationContext:提供一个应用程序的上下文容器
	 * WebAppliactionContext:应用于Web的上下文容器
	 */
	public void framkeOfSpringIOC(){
		
	}
	/**
	* 1:BeanFacory:主要是提供IOC容器的最基本功能，比如获取Bean等

	2:ListableBeanFactory:是BeanFactory的子类，主要提供了 列举Bean定义信息的功能。

	3:HierachicalBeanFactory：提供获取父工厂的功能

	4:ConfigureableBeanFactory extends HierarchicalBeanFactory, SingletonBeanRegistry : 可以配置父工厂,设置类加载器,设置缓存，
	可以注册别名，注册单例，设置类型转换器，BeanPostProcesser,配置Bean的依赖.

	5: AutowireCapableBeanFactory: 提供自动装配功能的功能，并且可以曝露给bean实例使用.
	包括可以创建bean,注入bean实例,配置Bean,解决依赖关系.
	6: BeanDefinitionRegistry ：继承了AliasRegistry，主要用来注册Bean的定义.提供了注册Bean定义，管理Bean定义，获取Bean定义,获取所以的注册的Bean定义名。

	7:SingletonBeanRegistry：这个接口用来注册共享的实例。
	类：
	Bean注册相关类：
	DefaultSingletonBeanRegistry : 实现了SingletonBeanRegistry接口，注册共享实例
	FactoryBeanRegistrySupport ： 继承了DefaultSingletonBeanRegistry 类, 支持FactoryBean的管理，获取 FactoryBean 、 FactoryBean 的类型、获取 FactoryBean 曝露的目标对象等，而这些功能都是基于附接口对 Bean 的注册功能的.

	AbstractBeanFactory ：继承了FactoryBeanRegistrySupport ，并且实现了ConfigureableBeanFactory .

	AbstractAutowireCapableBeanFactory :继承了AbstractBeanFactory ，并且实现了AutowireCapableBeanFactory,除了AbstractBean工厂外，并且具有自动装配Bean的功能.

	具体实现类：DefaultListableBeanFactory 
	public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory   implements ConfigurableListableBeanFactory, BeanDefinitionRegistry, Serializable

	从定义上看DefaultListableBeanFactory继承了AbstractAutowireCapableBeanFactory 具有了Bean的配置，创建管理和自动装配Bean的功能。
	并且实现了ConfigurableListableBeanFactory,BeanDefinitionRegistry接口，实现了Bean定义信息的列表和Bean定义注册相关功能。

	总结：从以上的体系结构的描述，我们可以知道,一个BeanFactory主要有下列：
	1:Bean的定义
	2:Bean的配置
	3:Bean的创建和注册
	4:从Bean工厂获取Bean和其他一些相关信息.
	所以IOC容器的核心接口是BeanFactory,而它的实现类是DefaultListableBeanFactory .
	 */
	public void studyBeanFactory(){
		
	}
	
	/**
	 * 学习DefaultListableBeanFactory
	 * 
	 */
	public void studyDefaultListableBeanFactory(){
		
	}
	
	/**
	 * ApplicationContext接口：
	 */
	public void studyApplicationContext(){
		
	}
	
	/**
	 * 
	 */
	public void studyWebAppliacationContext(){
		
	}
}
