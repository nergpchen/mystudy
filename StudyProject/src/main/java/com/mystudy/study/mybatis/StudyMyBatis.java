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
 * 		7:MyBatis的使用:配置文件学习、SqlMap文件学习、动态SQL
 * MyBatis进阶知识：
 *      1:MyBatis核心代码的源码分析 : 
 *      2:工作流程：
 *      3:使用注意事项
 *      4:原理
 *      
 * @author chenxiong
 *
 */
public class StudyMyBatis {

	/**
	 *  MyBatis is a Java persistence framework that couples objects with stored procedures or SQL statements using an XML descriptor or annotations.
	 *	MyBatis is free software that is distributed under the Apache License 2.0.
	 *	MyBatis is a fork of iBATIS 3.0 and is maintained by a team that includes the original creators of iBATIS.
	 *  MyBatis是一个java数据库持久层框架,支持定制SQL,存储过程,xml描述或者注解的配置,由iBATIS 3.0的一个分支.
	 *  MyBatis使用XML描述或者声明来配置和原始映射,然后把SQL语句和对象映射起来,也就是我们自己编写Sql语句，根据规则配置好，然后系统会自动映射sql结果到对象。
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
          MyBatis提供 一个映射引擎以声明的方式用来映射sql结果到对象.
     *  5:SQL statements can be built dynamically by using a built-in language with XML-like syntax or with Apache Velocity using the Velocity integration plugin.
          Sql statements对象可以用XML描述来动态创建
     *  6:MyBatis integrates with Spring Framework and Google Guice. This feature allows one to build business code free of dependencies.
          MyBatis 可以和Spriing框架或者Google Guice对象集成。
     *  7:MyBatis supports declarative data caching. A statement can be marked as cacheable so any data retrieved from the database will be stored in a cache and future executions of that statement will retrieve the cached data instead hitting the database. MyBatis provides a default cache implementation based on a Java HashMap and default connectors for integrating with: OSCache, Ehcache, Hazelcast and Memcached. It provides an API to plug other cache implementations.
          MyBatis支持声明式缓存. 一个语句可以被标记在缓冲中
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
	 
	 * 3:调用SqlSesson的api执行增删改查     :
	 
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
	 * Executor、
	 * StatementHandler
	 */
	public void 需要学习的类(){
		
	}
	
	public void SqlSessionFactoryBuilder(){
		
	}
	
	
	
	public void SqlSession(){
		
	}
	
	/**
	 * http://www.mybatis.org/mybatis-3/zh/configuration.html
	 * MyBatis的配置文件mybatis-config.xml是框架的核心文件,文档的元素如下：
	   properties 属性           	 : 这些属性都是可外部配置且可动态替换的，既可以在典型的 Java 属性文件中配置，亦可通过 properties 元素的子元素来传递。例如：
	   settings 设置                 	 : 这是 MyBatis 中极为重要的调整设置，它们会改变 MyBatis 的运行时行为。下表描述了设置中各项的意图、默认值等，比如设置参数cacheEnabled:该配置影响的所有映射器中配置的缓存的全局开关。
	   typeAliases 类型命名	 :类型别名是为 Java 类型设置一个短的名字。它只和 XML 配置有关，存在的意义仅在于用来减少类完全限定名的冗余。
	   typeHandlers 类型处理器       :	无论是 MyBatis 在预处理语句（PreparedStatement）中设置一个参数时，还是从结果集中取出一个值时， 都会用类型处理器将获取的值以合适的方式转换成 Java 类型。下表描述了一些默认的类型处理器。
       objectFactory 对象工厂        :MyBatis 每次创建结果对象的新实例时，它都会使用一个对象工厂（ObjectFactory）实例来完成。 默认的对象工厂需要做的仅仅是实例化目标类，要么通过默认构造方法，要么在参数映射存在的时候通过参数构造方法来实例化。 如果想覆盖对象工厂的默认行为，则可以通过创建自己的对象工厂来实现。
	   plugins 插件                                  :MyBatis 允许你在已映射语句执行过程中的某一点进行拦截调用。默认情况下，MyBatis 允许使用插件来拦截的方法调用包括：
	   environments 环境                    :MyBatis 可以配置成适应多种环境，这种机制有助于将 SQL 映射应用于多种数据库之中， 现实情况下有多种理由需要这么做。例如，开发、测试和生产环境需要有不同的配置；或者共享相同 Schema 的多个生产数据库， 想使用相同的 SQL 映射。许多类似的用例。
	   transactionManager 事务管理器 :在 MyBatis 中有两种类型的事务管理器（也就是 type=”[JDBC|MANAGED]”）：JDBC – 这个配置就是直接使用了 JDBC 的提交和回滚设置，它依赖于从数据源得到的连接来管理事务范围。
					              MANAGED – 这个配置几乎没做什么。它从来不提交或回滚一个连接，而是让容器来管理事务的整个生命周期（比如 JEE 应用服务器的上下文）。 默认情况下它会关闭连接，然而一些容器并不希望这样，因此需要将 closeConnection 属性设置为 false 来阻止它默认的关闭行为。例如:
   								     如果你正在使用 Spring + MyBatis，则没有必要配置事务管理器， 因为 Spring 模块会使用自带的管理器来覆盖前面的配置。
       dataSource 数据源      dataSource 元素使用标准的 JDBC 数据源接口来配置 JDBC 连接对象的资源。
       databaseIdProvider 数据库厂商标识 :MyBatis 可以根据不同的数据库厂商执行不同的语句，这种多厂商的支持是基于映射语句中的 databaseId 属性。 MyBatis 会加载不带 databaseId 属性和带有匹配当前数据库 databaseId 属性的所有语句。 如果同时找到带有 databaseId 和不带 databaseId 的相同语句，则后者会被舍弃。 为支持多厂商特性只要像下面这样在 mybatis-config.xml 文件中加入 databaseIdProvider 即可：
       mappers 映射器 :         既然 MyBatis 的行为已经由上述元素配置完了，我们现在就要定义 SQL 映射语句了。但是首先我们需要告诉 MyBatis 到哪里去找到这些语句。 Java 在自动查找这方面没有提供一个很好的方法，所以最佳的方式是告诉 MyBatis 到哪里去找映射文件。你可以使用相对于类路径的资源引用， 或完全限定资源定位符（包括 file:/// 的 URL），或类名和包名等
	 */
	public void 配置文件学习(){
		
	}
	/**
	 * 学习资料：http://www.mybatis.org/mybatis-3/zh/sqlmap-xml.html
	 * 我们所有的数据库sql语句都是通过Sql映射文件来操作，所以这个是我们学习的重点,学习如何来定义Sql语句
	 * SQL映射文件用来描述Sql语句,通常有以下几个元素：
	 *  cache – 给定命名空间的缓存配置。
		cache-ref – 其他命名空间缓存配置的引用。
		resultMap – 是最复杂也是最强大的元素，用来描述如何从数据库结果集中来加载对象。
		sql – 可被其他语句引用的可重用语句块。
		insert – 映射插入语句
		update – 映射更新语句
		delete – 映射删除语句
		select – 映射查询语句
	 */
	public void sqlMap文件学习(){
		
	}
	/**
	 * http://www.mybatis.org/mybatis-3/zh/dynamic-sql.html#
	 * 动态Sql是一个强大的特性,
	 */
	public void 动态Sql(){
		
	}
}
