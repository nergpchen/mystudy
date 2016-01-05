package com.mystudy.study.transaction;

/**
 * 数据库事务学习:
 * 资料：https://en.wikipedia.org/wiki/Database_transaction
 * 基本知识:
 * 1:什么是数据库的事务?
 * 2:事务的目的？
 * 3：相关基本概念.
 * 4:数据库事务的组成?
 * 5:事务的分类有哪些？
 * 6:如何操作事务：什么使用事务是正确的？什么使用事务是错误的？使用的时候需要注意的地方有哪些？
 * 7：有哪些核心的知识.
 * 
 * @author Administrator
 *
 */
public class StudyDataTransaction {

	/**
	 * A transaction symbolizes a unit of work performed within a database management system (or similar system) against a database, and treated in a coherent and reliable way independent of other transactions.
	 * 事务(Transaction)是访问并可能更新数据库中各种数据项的一个程序执行单元(unit)：事务是一个程序执行单元,访问数据库并且有可能更新数据库的一个执行单元,
	 * 特性：事务应该具有4个属性：原子性、一致性、隔离性、持久性。这四个属性通常称为ACID特性。
	 * 分类：
	 * 一个事务是对一系列动作的封装成一个事务动作.
	 */
	public void 什么是事务(){
		
	}
	/**
	 *  Databases and other data stores which treat the integrity of data as paramount often include the ability to handle transactions to maintain the integrity of data. A single transaction consists of one or more independent units of work, each reading and/or writing information to a database or other data store. When this happens it is often important to ensure that all such processing leaves the database or data store in a consistent state.
	 *  Examples from double-entry accounting systems often illustrate the concept of transactions. In double-entry accounting every debit requires the recording of an associated credit. If one writes a check for $100 to buy groceries, a transactional double-entry accounting system must record the following two entries to cover the single transaction:
     *  Debit $100 to Groceries Expense Account
     *  Credit $100 to Checking Account
     *  A transactional system would make both entries pass or both entries would fail. By treating the recording of multiple entries as an atomic transactional unit of work the system maintains the integrity of the data recorded. In other words, nobody ends up with a situation in which a debit is recorded but no associated credit is recorded, or vice versa.
     *  事务的功能是用来管理数据库操作的完整性.
     *  一个事务有单个或多个操作组成,这些操作可以是从数据库读或者写信息.
     *  
	 */
	
	public void 事务的目的(){
		
	}
	/**
	 * 原子性: 保持操作的一致性，事务里的操作要么都执行，要么都不执行.
	 * 一致性： 针对的是数据库里的数据，要保持数据的一致性，比如下订单的时候，当用户支付的时候，商品购买成功，同时要扣除掉用户的钱，商品的库存也要扣掉，如果用户支付失败，则商品库存不能扣掉，用户的钱如果扣掉的话则要还给用户。
	 * 隔离性: 一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
	 * 持久性:  持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。
	 */
	public void 事务的特点(){
		
	}

	
	/**http://www.cnblogs.com/qanholas/archive/2012/01/02/2310164.html
	 * 原子性: 保持操作的一致性，事务里的操作要么都执行，要么都不执行.
	 * 一致性： 针对的是数据库里的数据，要保持数据的一致性，比如下订单的时候，当用户支付的时候，商品购买成功，同时要扣除掉用户的钱，商品的库存也要扣掉，如果用户支付失败，则商品库存不能扣掉，用户的钱如果扣掉的话则要还给用户。
	 * 隔离性: 一个事务的执行不能被其他事务干扰。即一个事务内部的操作及使用的数据对并发的其他事务是隔离的，并发执行的各个事务之间不能互相干扰。
	 * 持久性:  持久性也称永久性（permanence），指一个事务一旦提交，它对数据库中数据的改变就应该是永久性的。接下来的其他操作或故障不应该对其有任何影响。
	 * 事务的隔离级别：
	 * 1：Read Uncommitted ：允许读取事务未提交操作的数据，如果一个事务已经开始写数据，则另外一个数据则不允许同时进行写操作，但允许其他事务读此行数据。该隔离级别可以通过“排他写锁”实现。
	 * 2：Read Committed   ： 允许不可重复读取，但不允许脏读取。这可以通过“瞬间共享读锁”和“排他写锁”实现。读取数据的事务允许其他事务继续访问该行数据，但是未提交的写事务将会禁止其他事务访问该行。
	 * 3：Repeatable Read  : 禁止不可重复读取和脏读取，但是有时可能出现幻影数据。这可以通过“共享读锁”和“排他写锁”实现。读取数据的事务将会禁止写事务（但允许读事务），写事务则禁止任何其他事务
	 * 4：序列化（Serializable）：提供严格的事务隔离。它要求事务序列化执行，事务只能一个接着一个地执行，但不能并发执行。如果仅仅通过“行级锁”是无法实现事务序列化的，必须通过其他机制保证新插入的数据不会被刚执行查询操作的事务访问到。
	 */
	public void 事务的相关基本概念(){
		
	}
	
	/**
	 * http://www.cnblogs.com/kissdodog/archive/2013/07/03/3169788.html
	 * 1: 开始事务： Begin 
	 * 2: 提交事务
	 * 3: 回滚事务
	 * 4: 保存事务：
	 */
	public void 操作(){
		
	}
}
