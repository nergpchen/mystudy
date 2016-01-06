package com.mystudy.study.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.session.defaults.DefaultSqlSession;
import org.apache.ibatis.session.defaults.DefaultSqlSessionFactory;
import org.apache.ibatis.transaction.Transaction;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.shiro.session.Session;

/**
 * 学习MyBatis框架.
 * 学习资料：
 * 1:http://www.bubuko.com/infodetail-549184.html
 * 2:http://mybatis.org/mybatis-3/zh/index.html
 * 3:https://en.wikipedia.org/wiki/MyBatis
 * 4:http://www.cnblogs.com/xdp-gacl/p/4261895.html
 * 
 * MyBatis基础知识:
 * 		1:MyBatis是什么：
 * 		2:MyBatis的作用:
 * 		3:MyBatis发展背景:
 * 		4:MyBatis的框架结构:
 * 		5:MyBatis的特点:
 * 		6:MyBatis的核心类:
 * 		7:MyBatis的使用:
 * MyBatis进阶知识：
 *      1:MyBatis核心代码的源码分析
 *      2:工作流程：
 *      3:使用注意事项
 *      4:原理
 * @author chenxiong
 *
 */
public class StudyMyBatis {

	/**
	 *  MyBatis is a Java persistence framework that couples objects with stored procedures or SQL statements using an XML descriptor or annotations.
	 *	MyBatis is free software that is distributed under the Apache License 2.0.
	 *	MyBatis is a fork of iBATIS 3.0 and is maintained by a team that includes the original creators of iBATIS.
	 *  MyBatis是一个java数据库持久层框架,支持定制SQL,存储过程,xml描述或者注解的配置,由iBATIS 3.0的一个分支.
	 */
	public void MyBatis介绍(){
		
	}
	/**
	 *  1:Unlike ORM frameworks, MyBatis does not map Java objects to database tables but Java methods to SQL statements.
	 *  和其他的ORM框架不同,Mybatis不是映射java对象到数据库表，而是映射java方法到sql命令.
	 *  2:MyBatis lets you use all your database functionality like stored procedures, views, queries of any complexity and vendor proprietary features. It is often a good choice for legacy or de-normalized databases or to obtain full control of SQL execution.
	 *   MyBatis框架可以使用数据库的所有功能比如视图，存储过程，查询或者其他.
	 *  3:It simplifies coding compared to JDBC. SQL statements are executed with a single line.
		
     *  4:MyBatis provides a mapping engine that maps SQL results to object trees in a declarative way.

     *  5:SQL statements can be built dynamically by using a built-in language with XML-like syntax or with Apache Velocity using the Velocity integration plugin.

     *  6:MyBatis integrates with Spring Framework and Google Guice. This feature allows one to build business code free of dependencies.

     *  7:MyBatis supports declarative data caching. A statement can be marked as cacheable so any data retrieved from the database will be stored in a cache and future executions of that statement will retrieve the cached data instead hitting the database. MyBatis provides a default cache implementation based on a Java HashMap and default connectors for integrating with: OSCache, Ehcache, Hazelcast and Memcached. It provides an API to plug other cache implementations.

	 */
	/**
	 * 
	 */
	public void MyBatis特点(){
		
	}
	/**这个是架构图的网址：
	 *     http://images.cnitblog.com/blog/576052/201312/25154351-1dee4d1910144841b23b95f2336c7134.png
	 * MyBatis的框架根据功能可以分为以三层:
	 * 		1:基础支撑层：包括连接管理、事务管理、配置加载和缓存处理和日志管理，负责最基础的功能支撑，这些都是共用的东西，将他们抽取出来作为最基础的组件。为上层的数据处理层提供最基础的支撑。
	 * 		2:数据处理层：负责具体的SQL查找、SQL解析、SQL执行和执行结果映射处理等  。                     它主要的目的是根据调用的请求完成一次数据库操作。
	 * 		3:API接口层：提供给外部使用的接口API包括增删改查和动态更改配置。 开发人员通过这些本地API来操纵数据库。接口层一接收到调用请求就会调用数据处理层来完成具体的数据处理。
	 * 基础支撑层：核心类是
	 * 数据库处理层：核心类是
	 * API接口层：   核心类是哪些？
	 * 
	 */
	public void 框架结构(){
		 
	}
	
	/**
	 * Mybaits既然是用来代替JDBC的链接的，那么工作流程又是怎么样呢？
	 * 下面是JDBC的大致流程
	 *  我们在使用JDBC来进行开发的时候，比如从数据库查询一个表,则需要先和数据库连接连接，获取一个连接对象：Connection ,通过Statement stmt = con.createStatement() ;  通过statement执行sql查询对象返回结果集Result,然后对获得的结果集进行处理。
	 *  那么MyBatis的流程如何呢？
	 * 1:通过SqlSessionFactoryBuilder建立一个SqlSessionFactory对象
	 * 2:从SqlSessionFactory返回一个SqlSession
	 * 3:调用sqlSesson的api执行增删改查     :
	 *    增删改查的处理过程：
			(A)根据SQL的ID查找对应的MappedStatement对象。
			(B)根据传入参数对象解析MappedStatement对象，得到最终要执行的SQL和执行传入参数。
			(C)获取数据库连接，根据得到的最终SQL语句和执行传入参数到数据库执行，并得到执行结果。
			(D)根据MappedStatement对象中的结果映射配置对得到的执行结果进行转换处理，并得到最终的处理结果。
			(E)释放连接资源。
	  4:返回处理结果将最终的处理结果返回。
	  所以从上面的流程可以知道，首先和数据库建立连接,传入参数，解析Sql语句,得到执行结果，映射执行结果
	    在这个过程中涉及到的类如下：SqlSessionFactoryBuilder、SqlSession、MappedStatement、Executor、StatementHandler
	        
	 */
	public void 工作流程(){
		
	}
	
	
	/**
	 * SqlSessionFactoryBuilder、
	 * SqlSession、
	 * MappedStatement、
	 * Executro、
	 * StatementHandler
	 */
	public void 需要学习的类(){
		
	}
	
	/**
	 * http://www.mybatis.org/mybatis-3/zh/dynamic-sql.html#
	 * 动态Sql是一个强大的特性,
	 */
	public void 动态Sql(){
		
	}
}
