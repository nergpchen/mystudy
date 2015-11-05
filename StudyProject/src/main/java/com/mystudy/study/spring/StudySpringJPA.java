package com.mystudy.study.spring;

/**
 * http://www.cnblogs.com/holbrook/archive/2012/12/30/2839842.html#sec-1
 * 1:什么是SpringJPA,干嘛用的？ 特性是什么?重点是什么？有哪些核心？ API有哪些？适用场景呢？ 怎么使用,Demo有
 * 
 * @author Administrator
 *
 */
public class StudySpringJPA {

	/**
	 * JPA（Java Persistence API，Java持久化API），定义了对象-关系映射（ORM）以及实体对象持久化的标准接口。
	 * 哪些接口呢？ 按照功能分为三部分: 1:定义ORM关系. 2:实体操作API:CRUD:操作的API涉及到创建会话,管理事务，增删改查的操作等待。
	 * 3:查询语言 重要概念： 1:实体生命周期. ORM: JPA提供了
	 * xml或者声明来描述实体间的映射关系。比如@entity,@Table,@Column,@ID
	 * 提供了ID的生成策略GeneratorType.AUTO ，由JPA自动生成
	 * GenerationType.IDENTITY，使用数据库的自增长字段，需要数据库的支持（如SQL
	 * Server、MySQL、DB2、Derby等）
	 * GenerationType.SEQUENCE，使用数据库的序列号，需要数据库的支持（如Oracle）
	 * GenerationType.TABLE，使用指定的数据库表记录ID的增长
	 * 需要定义一个TableGenerator，在@GeneratedValue中引用。例如：
	 * 
	 * @TableGenerator( name="myGenerator", table="GENERATORTABLE", pkColumnName
	 *                  = "ENTITYNAME", pkColumnValue="MyEntity",
	 *                  valueColumnName = "PKVALUE", allocationSize=1 )
	 * @GeneratedValue(strategy = GenerationType.TABLE,generator="myGenerator")
	 */
	public void whatIsJPA() {

	}

	public void TheAPIOFJPA() {
		System.out.println("学习 JPA的重要API:");
	}

	/**
	 * ORM: JPA提供了 xml或者声明来描述实体间的映射关系。比如@entity,@Table,@Column,@ID
	 * 提供了ID的生成策略GeneratorType.AUTO ，由JPA自动生成
	 * GenerationType.IDENTITY，使用数据库的自增长字段，需要数据库的支持（如SQL
	 * Server、MySQL、DB2、Derby等）
	 * GenerationType.SEQUENCE，使用数据库的序列号，需要数据库的支持（如Oracle）
	 * GenerationType.TABLE，使用指定的数据库表记录ID的增长
	 * 需要定义一个TableGenerator，在@GeneratedValue中引用。例如：
	 * 
	 * @TableGenerator( name="myGenerator", table="GENERATORTABLE", pkColumnName
	 *                  = "ENTITYNAME", pkColumnValue="MyEntity",
	 *                  valueColumnName = "PKVALUE", allocationSize=1 )
	 */
	public void ormOfJPA() {

	}
}
