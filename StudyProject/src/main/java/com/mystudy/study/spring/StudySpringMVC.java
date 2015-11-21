package com.mystudy.study.spring;

/**
 * http://jinnianshilongnian.iteye.com/blog/1594806 SpringMVC的学习：
 * 1:SpringMVC是什么？ 2:SpringMVC的目的是什么？用来干嘛 3:SringMVC怎么用？ 4:SpringMVC的框架结构
 * 5:SpringMVC的特点
 * 
 * @author Administrator
 *
 */
public class StudySpringMVC {

	/**
	 * Spring MVC
	 * 是Spring开源的实现了MVC模型的一个快速开发的Web框架。把Web层进行职责解耦，分为Model,View,Controller三层。
	 * 在Spring中
	 * ,Model一般是POJO对象，控制器通常是DispatcherServlet,View支持多种表现试图技术，包括JSP技术，Velocity
	 * 
	 * 技术背景： 
	 * 是什么：一个web框架.
	 * 干嘛用的：用来进行web应用开发，提供开发效率和速度. 
	 * 怎么用的？按照文档和官方demo写就可以。
	 */
	public void wahtIsSpringMVC() {

	}

	/**
	 * 特点：√让我们能非常简单的设计出干净的Web层和薄薄的Web层； √进行更简洁的Web层的开发；
	 * √天生与Spring框架集成（如IoC容器、AOP等）； √提供强大的约定大于配置的契约式编程支持； √能简单的进行Web层的单元测试；
	 * √支持灵活的URL到页面控制器的映射；
	 * √非常容易与其他视图技术集成，如Velocity、FreeMarker等等，因为模型数据不放在特定的API里
	 * ，而是放在一个Model里（Map数据结构实现，因此很容易被其他框架使用）；
	 * √非常灵活的数据验证、格式化和数据绑定机制，能使用任何对象进行数据绑定，不必实现特定框架的API； √提供一套强大的JSP标签库，简化JSP开发；
	 * √支持灵活的本地化、主题等解析； √更加简单的异常处理； √对静态资源的支持； √支持Restful风格。
	 */
	public void 特点是什么() {

	}
    /**
     * 可以查看SpringMVC源码的demo:
     * 1：在web.xml中配置SpringMVC的Servlet(真正理解Servlet是什么)
     * 2:定义一个类,TestController,用@Controller来声明这个类，这样就定义了一个控制器
     * 3：启动容器比如tomcat,通过url访问，这样就是完成了一个web请求。
     * 1:配置web.xml
     * <servlet>  
    <servlet-name>springmvcservlet</servlet-name>  
    <servlet-class>org.springframework.web.servlet.DispatcherServlet</servlet-class>  
    <load-on-startup>1</load-on-startup>  
	</servlet>  
	<servlet-mapping>  
    <servlet-name>springmvcservlet</servlet-name>  
    <url-pattern>/</url-pattern>  
	</servlet-mapping> 
	定义一个控制器：
	@Controller
	public class AppJsonApiController {
	@RequestMapping(value = "/test")
	@ResponseBody
	public ModelAndView test(){
		}
	}
	上面设计到了DispatcherServlet,ModelAndView,@Controller,@Requestmapping,@ResponseBody等技术点，那么到底怎么实现的呢？
	根据上面的简单流程就会想到：
	1：DispatcherServlet类怎么处理的呢？这个需要去学习DispatcherServlet的源码
	2：@Controller注解如何处理的
	3：如果做过web开发，web容器封装的数据都是通过request,response的？那么SpringMVC框架是如何绑定数据的？如何把request对象的数据转变成Model的数据转换。
	
    */
	public void 怎么使用(){
	}
	/** 
	 * 框架：框架的结构怎么样的？核心有哪些？核心类有哪些？整个流程是什么样的？原理有哪些呢？
	 * MVC的三层结构如何设计的？如何交互？核心类是什么，源码如何学习 核心类：DispatcherServlet.
	 * 1:DispatcherServlet:首先是一个Servlet，是一个调度控制器，提供SrpignMVC的集中访问点，负责职责的分派工作。比如把请求映射到具体的控制器，
	 * 2:Controller控制器，是MVC中的部分C:
	package org.springframework.web.servlet.mvc;
		public interface Controller {
       ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception;
	}
	主要职责：
	1、收集、验证请求参数并绑定到命令对象；
	2、将命令对象交给业务对象，由业务对象处理并返回模型数据；
	3、返回ModelAndView（Model部分是业务对象返回的模型数据，视图部分为逻辑视图名）。
	 * 
	 */
	public void 框架介绍() {

	}
	
	/**
	 * 要学习DispatherServlet类是如何请求的:学习DispatcherServlet类的学习
	 */
	public void studyDispatcherServlet(){
		
	}
	
	public void study数据绑定(){
		
	}
}
