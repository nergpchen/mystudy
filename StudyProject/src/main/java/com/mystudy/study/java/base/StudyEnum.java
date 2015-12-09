package com.mystudy.study.java.base;
/**引用资料：
 * 1:https://en.wikipedia.org/wiki/Enumerated_type
 * 2:http://docs.oracle.com/javase/1.5.0/docs/guide/language/enums.html
 * 枚举的学习：
 * 基本概念：
 * 1：枚举的定义：枚举是什么？不是什么?有相似的概念吗？
 * 2:枚举的用途：枚举用在哪里？
 * 3:枚举解决了什么问题？
 * 4:
 * @author Administrator
 *
 */
public class StudyEnum {

	/**
	 * In computer programming,
	 *  an enumerated type (also called enumeration or enum, or factor in the R programming language, and a categorical variable in statistics) is a data type consisting of a set of named values called elements, members or enumerators of the type
	 * 枚举是一种数据类型，是一种常量的集合
	 */
	public void 枚举的定义(){
		
	}
	
	public void 发展历史(){
		
	}
	public void 特点(){
		
	}
	public void 分类(){
		
	}
	public void 用法(){
		
	}
	/**
	 *  enum 的语法结构尽管和 class 的语法不一样，但是经过编译器编译之后产生的是一个class文件。该class文件经过反编译可以看到实际上是生成了一个类，该类继承了java.lang.Enum<E>。EnumTest 经过反编译(javap com.hmw.test.EnumTest 命令)之后得到的内容如下：
	*
	public class com.hmw.test.EnumTest extends java.lang.Enum{
    public static final com.hmw.test.EnumTest MON;
    public static final com.hmw.test.EnumTest TUE;
    public static final com.hmw.test.EnumTest WED;
    public static final com.hmw.test.EnumTest THU;
    public static final com.hmw.test.EnumTest FRI;
    public static final com.hmw.test.EnumTest SAT;
    public static final com.hmw.test.EnumTest SUN;
    static {};
    public int getValue();
    public boolean isRest();
    public static com.hmw.test.EnumTest[] values();
    public static com.hmw.test.EnumTest valueOf(java.lang.String);
    com.hmw.test.EnumTest(java.lang.String, int, int, com.hmw.test.EnumTest);
}	
	 */
	public void 原理分析(){
		
	}
}
