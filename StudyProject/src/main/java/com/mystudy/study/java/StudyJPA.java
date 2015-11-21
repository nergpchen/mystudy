package com.mystudy.study.java;
/**
 * 学习JPA
 * 1:JPA是什么？
 * 2:JPA用来干嘛呢？相似的框架由哪些
 * 3:JPA的体系结构.
 * 4:JPA的重点知识点.
 * 5:JPA的注解
 * 6:JPA的使用
 * 
 * @author Administrator
 *
 */
public class StudyJPA {

	/**1:@Entity:声明一个实体
	 * 2:@MappedSuperclass:声明一个子类的父类属性，会自动映射到子类的实体中
	 * 3:@Access(AccessType.FIELD):通常和@MappedSuperclass一起用
	 * 4:@id:定义主键
	 * 5:@GeneratedValue(generator="generator"):指定主键的值的产生策略
	 * 下面这个可用用来自定义主键
	 * 6:@GenericGenerator(name="",strategy="net.timshaw.appframework.dao.PersistantEntityIdGenerator")
	 */
	public void 注解学习(){
		
	}
	
	//JPA查询框架：
	public void 查询(){
		
	}
}
