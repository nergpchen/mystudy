package com.mystudy.study.spring;

/**
 *IOC的基本知识：
 *1:什么是IOC?
 *2:IOC的用处
 *3:IOC解决了什么问题？
 *4:IOC的意义
 *5:IOC的组成
 *6:IOC的一些基本知识点 
 * @author Administrator
 *
 */
public class StudyIOC {

	/**
	 * IOC是什么
	 * IOC的英文名的全称是Inversion of Control 也就是是控制反转,是一种编程的模式。控制反转指的是对象的获得被反转。
	         传统的面向对象编程模式下，我们通常是在代码中 通过 new 一个对象来获取一个对象，而控制反转是通过从所谓的IOC容器中来获取对象。
	         也就是从一个第三方的IOC容器来管理对象和对象间的关系。
	   IOC的技术是Spring框架的核心。   
	 * 
	 */
	public void whatIsIOC(){
		
	}
	
	/**
	 * IOC可以降低了对象间的耦合度，便于创建管理对象和对象间的关系。
	 * 比如在A类中，可能需要用到5个其他的对象，那么不需要在代码中 通过new创建，而是从容器中获取对象。
	 */
	public void characteristicsOfIOC(){
		
	}
	
	/**
	 * 从定义可以知道，IOC容器的基本功能就是可以获取到需要的对象也称为Bean,
	 * 所以IOC容器具有创建对象的功能.
	 * 那么如何创建对象呢？
	 * 比如一个A类，需要用到B类,C类，那么在创建A对象的时候,需要先创建到B对象和C对象，然后再创建A对象的时候，把B,C对象注入到A对象中去.
	 * 所有首先要定义好对象间的关系。
	 * 定义好后，然后根据配置文件的描述，分别创建对象。
	 * 那么在创建好后，就可以从IOC容器中获取了。
	 * 我们也可以创建一个最简单的IOC的容器。
	 *
	 */
	public void  IOC实现原理(){
		
	}
}
