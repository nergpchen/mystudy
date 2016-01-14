package com.mystudy.study.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.exceptions.ExceptionFactory;
import org.apache.ibatis.executor.ErrorContext;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.executor.ExecutorException;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.session.LocalCacheScope;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;
/**
 * 这个类主要是如何使用Mybatis类.
 * 
 * @author Administrator
 *
 */
public class StudyUseMyBatis {
  

	
	/**
	 * 从xml文件构建SqlSessionFactory:
	 * 过程分析:
	 * 1:SqlSessionFactoryBuilder.build():先通过XMLConfigBuilder.parse()创建一个Configuration对象
	 * 2:然后通过 new DefaultSqlSessionFactory(Configuration) 构建的SqlSessionFactoryBuilder对象,Configuration对象作为参数传入进去
	 * 
	 * 所以这里要学习的几个类包括：
	 *   1:SqlSessionFactoryBuilder:SqlSessionFactory构建器，使用了构建模式.
	 *   2:DefaultSqlSessionFactory :SqlSessionFactory的实现类，其中只有一个Configuration类型的对象的域
	 *   3:XMLConfigBuilder:用来对xml配置进行解析处理
	 *   4:Configuration  : 这个类是核心类，需要重点学习.存储相关的配置信息
	 */
	public SqlSessionFactory getSqlSessionFactory(){
		String resource = "mybatis-config.xml";
		InputStream inputStream = null;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);	
			
			return sqlSessionFactory;
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	/**
	 * 获取一个SqlSession:SqlSession用来执行命令，获取Mappers和管理事务(Through this interface you can execute commands, get mappers and manage transactions)
	 * 
	 * 通过SqlSessionFactory.openSession()获取一个SqlSession,重点是了解实现原理.
	 * 源码：下面是openSession()的源码：涉及到了几个类：Transaction、Environment、TransactionFactory、Executor、DefaultSqlSession、ErrorContext
	 * Transaction tx = null;
    try {
      final Environment environment = configuration.getEnvironment();
      final TransactionFactory transactionFactory = getTransactionFactoryFromEnvironment(environment);
      tx = transactionFactory.newTransaction(environment.getDataSource(), level, autoCommit);
      final Executor executor = configuration.newExecutor(tx, execType);
      return new DefaultSqlSession(configuration, executor, autoCommit);
    } catch (Exception e) {
      closeTransaction(tx); // may have fetched a connection so lets call close()
      throw ExceptionFactory.wrapException("Error opening session.  Cause: " + e, e);
    } finally {
      ErrorContext.instance().reset();
    }
     过程分析：1:首先通过Configuration对象获取一个Environment对象.
        2:通过getTransactionFactoryFromEnvironment(environment) 获得一个 TransactionFactory.
        3:创建一个事务对象：tx = transactionFactory.newTransaction()
        4:Executor对象：通过configuration.newExecutor()获取一个Executor对象.
        5:返回结果前执行：ErrorContext.instance().reset();
        6:返回一个SqlSession对象：new DefaultSqlSession(configuration, executor, autoCommit);

     在获取一个SqlSession中,先后建立一个Transaction对象和一个Executor对象，然后以参数形式传入到DefaultSqlSession构造函数中.
     需要学习的:
      1:SqlSessionAPI
      
      2:Transaction接口
      
      3:DefaultSqlSession的实现源码
      
      4:ErrorContext做什么用的.
	 */
	public SqlSession openSqlSession(){
		SqlSessionFactory sessionFactory = getSqlSessionFactory();
		SqlSession session = sessionFactory.openSession();
		return session;
	}
	/**
	 * 一：源码分析:
	 * session.selectList()这个代码执行的是DefaultSqlSession.selectList(String statement, Object parameter, RowBounds rowBounds);
	 * 代码如下： 
	 *  try {
      		MappedStatement ms = configuration.getMappedStatement(statement);
      		List<E> result = executor.query(ms, wrapCollection(parameter), rowBounds, Executor.NO_RESULT_HANDLER);
      		return result;
    		} catch (Exception e) {
      		throw ExceptionFactory.wrapException("Error querying database.  Cause: " + e, e);
    		} finally {
      		ErrorContext.instance().reset();
    	}
       	步骤：
       	1: 从configuration获取一个MappedStatement对象,这个对象是用来存储mybatis-config.xml配置文件里定义个一个节点，这个节点存储了当前的sql语句,参数以及返回值
       	2: Executor.query()执行查询结果.
	 *  
	 *  Executor.query()代码：
	 *  	下面代码是BaseExecutor类实现的代码
	 *       public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler) throws SQLException {
    			BoundSql boundSql = ms.getBoundSql(parameter);
    			CacheKey key = createCacheKey(ms, parameter, rowBounds, boundSql);
    			return query(ms, parameter, rowBounds, resultHandler, key, boundSql);
 			}
 	     1: 从MappedStatement对象中获取一个BoundSql对象.
 	     2: 建立一个CacheKey对象
 	     3: 执行query(ms, parameter, rowBounds, resultHandler, key, boundSql);
 	 
 	二：源码：
 	
 	下面是query(ms, parameter, rowBounds, resultHandler, key, boundSql) 的源代码   
  
  public <E> List<E> query(MappedStatement ms, Object parameter, RowBounds rowBounds, ResultHandler resultHandler, CacheKey key, BoundSql boundSql) throws SQLException {
    ErrorContext.instance().resource(ms.getResource()).activity("executing a query").object(ms.getId());
    if (closed) throw new ExecutorException("Executor was closed.");
    if (queryStack == 0 && ms.isFlushCacheRequired()) {
      clearLocalCache();
    }
    
    List<E> list;
    try {
      queryStack++;
      list = resultHandler == null ? (List<E>) localCache.getObject(key) : null;
      if (list != null) {
        handleLocallyCachedOutputParameters(ms, key, parameter, boundSql);
      } else {
        list = queryFromDatabase(ms, parameter, rowBounds, resultHandler, key, boundSql);
      }
    } finally {
      queryStack--;
    }
    if (queryStack == 0) {
      for (DeferredLoad deferredLoad : deferredLoads) {
        deferredLoad.load();
      }
      deferredLoads.clear(); // issue #601
      if (configuration.getLocalCacheScope() == LocalCacheScope.STATEMENT) {
        clearLocalCache(); // issue #482
      }
    }
    return list;
  }
  步骤： 1:判断运行器是否关了
     2:如果需要则调用clearLocalCache()清除缓存
     3:
	 */
	@Test
	public void select(){
		SqlSession session = openSqlSession();
		try{
			List<Object> result= session.selectList("selectid");
			System.out.println(result.size());
		}finally{
			session.close();
		}
		
	}
	/** 1:SqlSessionFactoryBuilder:SqlSessionFactory构建器，使用了构建模式.
	 *  2:DefaultSqlSessionFactory :SqlSessionFactory的实现类，其中只有一个Configuration类型的对象的域
	 *  3:XMLConfigBuilder:用来对xml配置进行解析处理
	 *  4:Configuration  : 这个类是核心类，需要重点学习.存储相关的配置信息
	 * 
	 */
	
	
}
