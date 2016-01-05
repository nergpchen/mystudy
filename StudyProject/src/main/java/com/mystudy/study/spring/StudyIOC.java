package com.mystudy.study.spring;

/**
 * 学习资料：
 * 1:https://en.wikipedia.org/wiki/Inversion_of_control
 * 2:http://www.cnblogs.com/xdp-gacl/p/4249939.html:
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
	 * IOC的英文名的全称是Inversion of Control 也就是是控制反转,是一种编程的模式.这种编程模式是把对象交由容器来管理，如果要获取对象从容器中获取，而不是自己直接通过new 来创建。
	 * 控制反转指的是对象的获得被反转。
	 * 传统的面向对象编程模式下，我们通常是在代码中 通过 new 一个对象来获取一个对象，而控制反转是通过从所谓的IOC容器中来获取对象。
	 * 也就是从一个第三方的IOC容器来管理对象和对象间的关系。
	 * IOC的技术是Spring框架的核心。   
	 * IOC的核心是IOC容器，通过一个IOC容器来对对象进行创建，管理对象间的依赖关系。
	 * 意义：
	 * 通过IOC容器来管理对象间的依赖关注可以降低对象间的耦合度。
	 * 如果没有IOC容器的话，在代码中，对象间的协作关系需要通过在代码中实现,比如我这个A类代码中需要用到B,C,D3个类，则需要再A代码中在合适的时机来创建代码,如果B类中需要用到A,C,D，则需要再B类代码中自己通过new()创建A,C,D对象.
	 * 这样对象间的耦合度就高了，通过IOC容器来管理对象间的关系，降低代码中对象间的耦合度。
	 */
	public void whatIsIOC(){
		
	}
	/**
	 * Sun ONE技术体系下的IOC容器有：轻量级的有Spring、Guice、Pico Container、Avalon、HiveMind；重量级的有EJB；不轻不重的有JBoss，Jdon等等。Spring框架作为Java开发中 SSH(Struts、Spring、Hibernate)三剑客之一，大中小项目中都有使用，非常成熟，应用广泛，EJB在关键性的工业级项目中也被使 用，比如某些电信业务。

　	　.Net技术体系下的IOC容器有：Spring.Net、Castle等等。Spring.Net是从Java的Spring移植过来的IOC容 器，Castle的IOC容器就是Windsor部分。它们均是轻量级的框架，比较成熟，其中Spring.Net已经被逐渐应用于各种项目中。
	 */
	public void IOC实现的框架介绍(){
		
	}
	/**优点：
	 * 通过IOC容器来管理对象间的依赖关注可以降低对象间的耦合度。
	 * 如果没有IOC容器的话，在代码中，对象间的协作关系需要通过在代码中实现,比如我这个A类代码中需要用到B,C,D3个类，则需要再A代码中在合适的时机来创建代码,如果B类中需要用到A,C,D，则需要再B类代码中自己通过new()创建A,C,D对象.
	 * 这样对象间的耦合度就高了，通过IOC容器来管理对象间的关系，降低代码中对象间的耦合度。
	 * 缺点:
	 * 1:软件系统中由于引入了第三方IOC容器，生成对象的步骤变得有些复杂，本来是两者之间的事情，又凭空多出一道手续，所以，我们在刚开始使用IOC框 架的时候，会感觉系统变得不太直观。所以，引入了一个全新的框架，就会增加团队成员学习和认识的培训成本，并且在以后的运行维护中，还得让新加入者具备同 样的知识体系。
	 * 2:由于IOC容器生成对象是通过反射方式，在运行效率上有一定的损耗.
	 */
	public void characteristicsOfIOC(){
		
	}
	
}
