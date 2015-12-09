package com.mystudy.study.spring;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.DispatcherServlet;
import org.springframework.web.servlet.HandlerAdapter;
import org.springframework.web.servlet.HandlerExecutionChain;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import org.springframework.web.servlet.mvc.LastModified;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

/**
 * http://jinnianshilongnian.iteye.com/blog/1594806 
 * SpringMVC基本知识的学习：
 * 1:SpringMVC是什么？ 
 * 2:SpringMVC的目的是什么？
 * 3:SringMVC怎么用？ 
 * 4:SpringMVC的框架结构:有哪些接口，之间如何交互。
 * 5:SpringMVC的特点
 * 技术点：
 * 1:SpringMVC如何处理Web的请求.
 * 2:Spring核心类DispatcherServlet学习
 * 3:HandlerMaping
 * 4:HandleAdapter
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
	 * http://jinnianshilongnian.iteye.com/blog/1594806
	 * SpringMVC处理是Web发出请求,是用DispatcherServlet类来处理请求。
	 * 1:前端控制器接收web请求,前端控制器根据请求信息（如URL）来决定选择哪一个页面控制器进行处理并把请求委托给它.
	 * 2:首先需要收集和绑定请求参数到一个对象,并进行验证，然后将命令对象委托给业务对象进行处理
	 * 3:处理完毕后返回一个ModelAndView（模型数据和逻辑视图名）
	 * 4:前端控制器收回控制权，然后根据返回的逻辑视图名，选择相应的视图进行渲染，并把模型数据传入以便视图渲染
	 * 5:前端控制器再次收回控制权，将响应返回给用户.
	 */
	public void 处理流程(){
		
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
	3：web容器封装的数据都是通过request,response的？那么SpringMVC框架是如何绑定数据的？如何把request对象的数据转变成Model的数据转换。
	
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
	 * SpringMVC是如何把url请求映射到相关的处理逻辑中.
	 * 是通过DispatherServlet来处理web请求.DispatcherServlet.doDispatch()来分派web请求：
	 * 1:根据web请求来映射处理器
	 * 2:根据映射处理器匹配处理适配器
	 * 4：适配器处理逻辑返回ModelView
	 * 5:试图解析
	 * 6:试图渲染
	 * 7:返回响应结果。
	 * 涉及到的类:HandlerMapping、HandlerAdapter、HandlerExecutionChain、HandlerInterceptor、ModelView
	 * 这些接口和类都是我们要学习的:接口的功能，实现类和之间的交互。
	 */
	public void study映射请求(){
	}
	
	/**
	 * /** 1:处理文件上传.
	 *  2:获得一个HandlerExecutionChain,这个是Handler执行链,由handler对象和一组HandlerInterceptor组成。
	 *  3:根据HandlerExecutionChain.handler获得对应的HandlerAdapter,用HadnlerAdapter.supports()方法来判断适配器是否支持Handler类
	 *  4:根据lastModified判断当前servlet请求是否需要重新刷新还是缓存,如果没有更新，则不用请求,中断处理
	 *  5:mappedHandler.applyPreHandle方法来执行拦截器的预处理
	 *  6:调用ha.handle(processedRequest, response, mappedHandler.getHandler())来处理实际业务逻辑
	 *  7:asyncManager.isConcurrentHandlingStarted()来判断是否是异步请求
	 *  9：解析视图
	 *  10:处理拦截器的postHandle
	 *  问题：WebAsyncManager:管理web异步请求
	 *  接下来学习几个核心类以及一些扩展类，核心类如何参与交互的以及如何扩展的。
	 *  1:如何获得执行链
	 *  HandlerExecutionChain:处理器执行链，包括Handler类和一组拦截器.
	 *  用getHandler()方法来获取执行链
	 *  代码：遍历handlerMappings获取合适的处理器,
	 *  for (HandlerMapping hm : this.handlerMappings) {
			if (logger.isTraceEnabled()) {
				logger.trace(
						"Testing handler map [" + hm + "] in DispatcherServlet with name '" + getServletName() + "'");
			}
			HandlerExecutionChain handler = hm.getHandler(request);
			if (handler != null) {
				return handler;
			}
		}
		return null;
		}
		
	HandlerExecutionChain:
	属性:
	private final Object handler;

	private HandlerInterceptor[] interceptors;

	private List<HandlerInterceptor> interceptorList;

	执行逻辑：
	1：在真正调用其handler对象前，HandlerInterceptor接口实现类组成的数组将会被遍历，
	2：其preHandle方法会被依次调用，然后真正的handler对象将被调用。
    3：handler对象被调用后，就生成了需要的响应数据，在将处理结果写到HttpServletResponse对象之前（SpringMVC称为渲染视图），其postHandle方法会被依次调用。视图渲染完成后，最后afterCompletion方法会被依次调用，整个web请求的处理过程就结束了
   

    	总结：在一个处理对象执行之前，之后利用拦截器做文章，这已经成为一种经典的框架设计套路
     	在代码中出现了HandlerMapping,handlerMappings.这两个又是什么呢？如何初始化？ HandlerInterceptor也是核心接口
   	二：HandlerInterceptor
   HandlerInterceptor，是SpringMVC的第二个扩展点的暴露，通过自定义拦截器，我们可以在一个请求被真正处理之前、请求被处理但还没输出到响应中、请求已经被输出到响应中之后这三个时间点去做任何我们想要做的事情。
   	比如LocaleChangeInterceptor是拦截器的一个实现类。用来处理国际化。
   
	三：HandlerAdapter
	boolean supports(Object handler);
	ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
	long getLastModified(HttpServletRequest request, Object handler);
	实现类：
	1:RequestMappingHandlerAdapter用来支持注解
	 */
	public void studyDispatcherServlet(){
		
	}
	
	/**http://my.oschina.net/lichhao/blog/99039
	 * {@link HandlerExecutionChain}
	 * HandlerExecutionChanin是一个类，称为执行链,由Handler和一组HandlerInterceptor拦截器组成。
	 * 这个通常从HandlerMapping类获取
	 * 提供了下面4个接口：
	 * applyPreHandle:
	 * applyPostHandle:
	 * triggerAfterCompletion
	 * applyAfterConcurrentHandlingStarted
	 * 通过前置处理、Post处理、后置处理.
	 * 这个执行链封装了处理类和拦截器。提供了以上4个用来执行各个阶段的方法。
	 * HandlerExecutionChain整个执行脉络也就清楚了：在真正调用其handler对象前，HandlerInterceptor接口实现类组成的数组将会被遍历，其preHandle方法会被依次调用，
	 * 然后真正的handler对象将被调用。
      handler对象被调用后，就生成了需要的响应数据，在将处理结果写到HttpServletResponse对象之前（SpringMVC称为渲染视图），其postHandle方法会被依次调用。视图渲染完成后，最后afterCompletion方法会被依次调用，整个web请求的处理过程就结束了
	 * 
	 */
	public void studyHandlerExecutionChain(){
		
	}
	
	/**http://my.oschina.net/lichhao/blog/99039
	 * {@link HandlerInterceptor}
	 * HandlerInterceptor是什么？
	 *yHandlerInterceptor是拦截器用来拦截用户的请求并进行相应的处理。 我们可以定义自己的拦截器。
	 *代码：通过下面代码可以看到总共有3个方法，提供了预处理、请求时间处理和提交后处理。在一个处理对象执行之前，之后利用拦截器做文章，这已经成为一种经典的框架设.
	 *ndlerInterceptor，是SpringMVC的第二个扩展点的暴露，通过自定义拦截器，我们可以在一个请求被真正处理之前、请求被处理但还没输出到响应中、请求已经被输出到响应中之后这三个时间点去做任何我们想要做的事情。
	 *Struts2框架的成功，就是源于这种拦截器的设计，SpringMVC吸收了这种设计思想，并推陈出新，更合理的划分了三个不同的时间点，从而给web请求处理这个流程，提供了更大的扩展性。
	public interface HandlerInterceptor {
 
    boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
        throws Exception;
 
    void postHandle(
            HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView)
            throws Exception;
 
    void afterCompletion(
            HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
            throws Exception;
 
}
	实现类：
在HandlerExecutionChain有一组拦截器，在处理的时候，如果HandlerExecutionChain有拦截器的话，则遍历执行handlerInterceptor.preHandle()方法.
*/
	public void studyHandlerInterceptor(){
		
	}
	/**HandlerAdapter:
	 * 1:接口：
	 *  boolean supports(Object handler); 
        ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception;
        long getLastModified(HttpServletRequest request, Object handler);
	 
	 * 2:功能：
	 * 从上面接口可以知道，HandlerAdapter用来处理匹配的Hadler,用supports来定义支持的Handler.handler方法来执行业务处理。
	
	 * 3:实现类：
	 * 1:SimpleServletHandlerAdapter
	 * 2:SimpleControllerHandlerAdapter:
	 * 3:RequestMappingHandlerAdapter:用来匹配注解方法
	 * SimpleControllerHandlerAdapter分析：
	 * SimpleControllerHandlerAdapter是一个适配器的实现，用来适配Controller接口
	 *  public class SimpleControllerHandlerAdapter implements HandlerAdapter {
		//判断是否是Contoller接口	
		public boolean supports(Object handler) {
		return (handler instanceof Controller);
		}
	//执行handler.handlerRequest(request,response)的真正业务处理逻辑
	public ModelAndView handle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		return ((Controller) handler).handleRequest(request, response);
	}

	public long getLastModified(HttpServletRequest request, Object handler) {
		if (handler instanceof LastModified) {
			return ((LastModified) handler).getLastModified(request);
		}
		return -1L;
	}
}
	 */
	public void studyHandlerAdapter(){
		
	}
	/**{@link HandlerMapping}
	 * 在DispatcherServlet.doDispatch()方法中:getHandler(HttpServletRequest request)从HandlerMappings中获取到执行链，
	 * 那么这个HandlerMapping是什么呢?如何获取呢？如何初始化呢？
	 * HandlerMapping:
	 * 接口：	HandlerExecutionChain getHandler(HttpServletRequest request) throws Exception;
	 * 只有1个接口getHandler()用来获取处理器，就是一个HandlerExecutionHandler对象,包括了实际的处理类和拦截器。
	 * 实现类：
	 * {@link RequestMappingHandlerMapping}
	 * 
	 */
	public void studyHandlerMapping(){
		
		
	}
	public void sutdyController(){
		
	}
	
	public void study数据绑定(){
		
	}
	/**
	 * 1：容器如何初始化RequestMapping注解的。
	 * 2：在请求的时候如何处理映射到RequestMapping的方法呢?
	 * 第一个问题：
	 *   1：在初始化容器的时候,有一个步骤：
	 *   AbstractAutowireCapableBeanFactory.initializeBeaninvoke
	 *   在这方法中有一个invokeInitMethods, 在这个代码中会执行bean.afterPropertiesSet()方法
	 * 
	 * 2：afterPropertiesSet()方法
	 * 方法是InitializingBean的一个接口，所有实现了这个接口，都要实现  afterPropertiesSet()方法。
	 *  在初始化bean的时候调用。
	 * 3:AbstractHandlerMethodMapping.afterPropertiesSet()
	 *  AbstractHandlerMethodMapping实现了这个接口，也就是实现了afterPropertiesSet()方法,
	 * 4:在初始化bean的时候会调用 AbstractHandlerMethodMapping.afterPropertiesSet()
	 * 
	 * 5: initHandlerMethods
	 * 在afterPropertiesSet()方法中调用了initHandlerMethods()方法.
	 * 这个方法用来从所有的bean中检测出符合规则的类和方法,
	 * 然后生成一个调用RequestMappingHandlerMapping.getMappingForMethod()生存一个RequestMappingInfo对象，
	 * 在registerHandlerMethod注册过程中：根据RequestMappingInfo对象生成一个HandlerMethod对象。
	 * 
	 * 6:注册到AbstractHandlerMethodMapping的handlerMethods和urlMap中.
	 * Map<T, HandlerMethod> handlerMethods;
	 * MultiValueMap<String, T> urlMap
	 * 代码分析：
	 *  我们来看看 这个方法initHandlerMethods()
	 *  
	 *  protected void initHandlerMethods() {
		if (logger.isDebugEnabled()) {
			logger.debug("Looking for request mappings in application context: " + getApplicationContext());
		}

		String[] beanNames = (this.detectHandlerMethodsInAncestorContexts ?
				BeanFactoryUtils.beanNamesForTypeIncludingAncestors(getApplicationContext(), Object.class) :
				getApplicationContext().getBeanNamesForType(Object.class));
		//调用isHandler()判断是否是处理器，如果是的话，则detectHandlerMethods()方法
		for (String beanName : beanNames) {
			if (isHandler(getApplicationContext().getType(beanName))){
				detectHandlerMethods(beanName);
			}
		}
		handlerMethodsInitialized(getHandlerMethods());
	}
	 1:加载容器bean
	 2:调用RequestMappingHandlerMapping.isHandler()判断是否是RequestMapping注解
	 3:如果是，则调用detectHandlerMethods()方法检测所有的处理器方法，在这个方法中提供了HandlerMethodSelector.selectMethods()
	         来匹配处理器方法.
	         注意在detectHandlerMethods()方法中调用getMappingForMethod()方法，这个方法会返回一个RequestMappingInfo对象
	 4：调用registerHandlerMethod()注册所有的匹配方法
	    在这个方法中会用HandlerMethod newHandlerMethod = createHandlerMethod(handler, method)
	    创建一个handlerMethod对象，保存到handlerMethods中
	  然后把url信息保存到urlMap 中.
	
	涉及到类:InitializingBean、AbstractHandlerMethodMapping、RequestMappingHandlerMapping、RequestMappingInfo、HandlerMethod对象
	 
	 代码分析：
	 
	 */
	public void mvcHowToInitRequestMapping(){
		
	}
	
	public void mvcHowToProcessRequestMapping(){
		
	}
}
