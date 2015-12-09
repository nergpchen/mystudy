package com.mystudy.study.spring;

import org.springframework.jdbc.support.nativejdbc.Jdbc4NativeJdbcExtractor;

import com.mystudy.study.java.base.StudyJavaProxy;

/**
 * 参考资料：https://en.wikipedia.org/wiki/Aspect-oriented_programming
 * AOP知识的学习需要了解以下知识点： AOP的基本概念： 
 * 1:AOP是什么?都有哪些属性? 
 * 2:AOP解决了什么问题，技术背景 
 * 3:AOP体系结构
 * 4:AOP的意义 5:AOP的特点有哪些？ 6:AOP的适用场景 7:AOP的注意事项 8:AOP如何实现的方法
 * 5:AOP相关技术点/相关学习资料/相关案例代码
 * 
 * @author Administrator
 *
 */
public class StudyAOP {

	/**
	 * https://en.wikipedia.org/wiki/Aspect-oriented_programming
	 * http://www.cnblogs.com/wayfarer/articles/241024.html AOP概念：
	 * AOP英文全称为Aspect Oriented
	 * Programming,面向切面编程,比如面向对象编程核心是对象，要去理解什么是对象，那么面向切面编程的核心是切面
	 * ，那么什么是切面呢？解决了什么问题？ 面向切面编程是一种编程思想,可以横切关注点分离来增加功能模块的一种编程技术。也就是说,
	 * 通过AOP技术我们可以在不改变方法内部代码的情况下给程序添加统一功能的一种技术.
	 * 而AOP技术则恰恰相反，它利用一种称为“横切”的技术，剖解开封装的对象内部，
	 * 并将那些影响了多个类的公共行为封装到一个可重用模块，并将其名为“Aspect”，即方面可以降低模块间的耦合度，并有利于未来的可操作性和可维护性。
	 * 然后把方面的代码可以不用修改类的内部代码的情况下,实现方面里的功能。 目的：可以把影响了多个类的公共行为封装到一个可重用模块，降低模块间的耦合度.
	 * 意义：AOP是OOP编程的补充，解决了OOP的一些缺点：。OOP引入封装、继承和多态性等概念来建立一种对象层次结构，用以模拟公共行为的一个集合,
	 * 但是对分散在各个类中的相同的行为却不能够公共的行为，比如日志功能，事务管理等待。
	 * 核心类：JdkDynamicAopProxy :
	 */
	public void whatIsAOP() {
		System.out.println("AOP是什么呢？");
	}

	/**
	 * AOP是一种新的编程思想，那么一定有一些基本的概念我们去要了解和学习的。 
	 * 1.通知(Advice):
	 * 通知定义了切面是什么以及何时使用。描述了切面要完成的工作和何时需要执行这个工作。
	 * 
	 * 2.连接点(Joinpoint): 程序能够应用通知的一个“时机”，这些“时机”就是连接点，例如方法被调用时、异常被抛出时等等。
	 * 
	 * 3.切入点(Pointcut) 通知定义了切面要发生的“故事”和时间，那么切入点就定义了“故事”发生的地点，例如某个类或方法的名称，
	 * Spring中允许我们方便的用正则表达式来指定
	 * 
	 * 4.切面(Aspect) 通知和切入点共同组成了切面：时间、地点和要发生的“故事”.
	 * 
	 * 5.引入(Introduction) 引入允许我们向现有的类添加新的方法和属性(Spring提供了一个方法注入的功能.
	 * 
	 * 6.目标(Target)
	 * 即被通知的对象，如果没有AOP,那么它的逻辑将要交叉别的事务逻辑，有了AOP之后它可以只关注自己要做的事(AOP让他做爱做的事).
	 * 
	 * 7.代理(proxy) 应用通知的对象，详细内容参见设计模式里面的代理模式
	 * 
	 * 8.织入(Weaving) 把切面应用到目标对象来创建新的代理对象的过程，织入一般发生在如下几个时机:
	 * (1)编译时：当一个类文件被编译时进行织入，这需要特殊的编译器才可以做的到，例如AspectJ的织入编译器
	 * (2)类加载时：使用特殊的ClassLoader在目标类被加载到程序之前增强类的字节代码
	 * (3)运行时：切面在运行的某个时刻被织入,SpringAOP就是以这种方式织入切面的，原理应该是使用了JDK的动态代理技术
	 */
	public void baseInfoOfAOP() {
		System.out.println("AOP的基本概念");
	}

	/**
	 * Authentication 权限 
	 * Caching 缓存 
	 * Context passing 内容传递 
	 * Error handling 错误处理
	 * Lazy loading　懒加载 
	 * Debugging　　调试 logging, tracing, profiling and
	 * monitoring　记录跟踪　优化　校准
	 * Performance optimization　性能优化 
	 * Persistence　　持久化
	 * Resource pooling　资源池 
	 * Synchronization　同步 Transactions 事务
	 */
	public void 适用场景() {
		System.out.println("AOP的适用场景");
	}

	/**Spring AOP的技术核心是生成代理对象，那么如何生成代理对象呢？
	 * 实现方式通常有两种：
	 * 1:JDK动态代理技术，通过代理代理实现字节码增强技术，是基于接口实现的。
	 * 2:cglib的proxy:
	 * CGLI是一个强大的，高性能，高质量的Code生成类库，它可以在运行期扩展Java类与实现Java接口。
	 * 主要是对类继承一个子类，并覆盖其中的方法。 
	 * Hibernate用它来实现PO(Persistent Object 持久化对象)字节码的动态生成。
	 */
	public void 实现方式(){
		System.out.println("实现方式");
	}
	
	/**动态代理技术描述：
	 * 动态代理是一种代理模式的一种模式,代理模式是指为某个对象提供一种代理，通过这个代理来访问某个对象，从而可以控制对对象的访问，或者提供一些其他的功能。
	 * 静态代理：由程序员创建或特定工具自动生成源代码，再对其编译。在程序运行前，代理类的.class文件就已经存在了。
	 * 那么动态代理模式是什么呢？是在程序运行期间,生成代理对象。
	 * java提供了动态代理的实现方式：在ava.lang.reflect包下提供了反射技术可以用来实现动态代理.
	 * 在 {@link StudyJavaProxy} 中详细描述了学习java动态代理技术的知识点.
	 * 这个主意是学习Spring的动态代理技术
	 * 动态代理技术的实现原理
	 * 动态代理技术设计到的类有哪些：
	 * Spring 提供了@JdkDynamicAopProxy 类来实现动态代理技术
	 */
	public void 学习动态代理技术(){
		
	}
}
