package com.mystudy.study.java.base;

import java.io.Serializable;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**引用资料：
 * 1：https://en.wikipedia.org/wiki/Generics_in_Java
 * 2:http://www.oracle.com/technetwork/java/javase/generics-tutorial-159168.pdf
 * java泛型学习：
 * 基本知识：
 * 泛型是什么？
 * 泛型的规则：语法的要有语法的规则，了解语法的规则，知道规则正确使用？
 * 泛型的分类？
 * 怎么使用泛型?
 * 
 * @author Administrator
 *
 */
public class StudyGeneric<T extends Object> {

	/**
	 * Generics are a facility of generic programming that were added to the Java programming language in 2004 within J2SE 5.0. They allow "a type or method to operate on objects of various types while providing compile-time type safety."[1] This feature specifies the type of objects stored in a Java Collection. In 1998, Philip Wadler created Generic Java, an extension to the Java language to support generic types.[2] Generic Java was incorporated, with the addition of wildcards, into the official Java language version J2SE 5.0.
	 * 泛型是在jdk1.5中加入到java语言中的。
	 * 泛型可以让一个类型或者方法可以操作不同类型的对象。这个特征在java集合类中可以看出.List类可以操纵不同类型的对象。
	 * 通过让类型变为一个参数传递进来，即类型参数化来达到泛型的特性.泛型特性我们可以用在类，接口和方法中。
	 * 也就是可以把类型当做参数传给类,接口和方法.
	 * 
	 */
	public void java泛型是什么(){
		
	}
	
	public void 泛型不是什么(){
		
	}
	/**
	 * 其他语言的泛型怎么样呢？
	 */
	public void java泛型相似的概念(){
		
	}
	/**
	 * 泛型是java语言的一个特点，是语法级别的，那么语法规则是什么样呢？
	 * 1:泛型的参数只能是类类型，不能使基本类型
	 * 2:同一种泛型可以对应多个版本,不同版本的泛型类实例是不兼容的。
	 * 3:泛型的类型参数可以多个
	 * 4:泛型的参数类型可以使用extends语句,例如 <T extends String>,表示这个类型是String的子类
	 * 5:泛型的参数类型可以是通配符 ,例如 Class<?> classType = Class.forName("java.lang.String");
	 * 6:泛型可以用在类，方法和接口，称为泛型类，泛型方法和泛型接口
	 * 例子:
	 * 泛型接口：
	 * 比如我们常用的java集合框架：
	 * public interface Iterable<T>:这个是泛型接口,<T>表示的是参数
	 * public interface Collection<E> extends Iterable<E> 
	 * public interface Map<K,V> : K和V都是表示的是类型的参数，比如Map<String,String>
	 * 泛型类：
	 * public class HashMap<K,V>    extends AbstractMap<K,V> implements Map<K,V>, Cloneable, Serializable
	 * 泛型方法:
	 * public V put(K key, V value)  
	 * 通配符泛型:
	 * <?>表示是任意类型:比如下面的List<?> list:
	 * <? extends String> 
	 * <? super String >:表示参数类型是String的父类
	 */
	public void 泛型规则(){
		List<?> list ;
		List<? extends String > list2;
		List<? super String> list3 = new ArrayList();
	}
	/**
	 * 泛型可以用在类，方法和接口，称为泛型类，泛型方法和泛型接口
	 */
	public void 分类(){
		
	}

	/**
	 * 了解了规则，就知道如何用了?
	 * 
	 */
	public void 用法(){
		List<?> list ;
		List<? extends String > list2;
		List<? super String> list3 = new ArrayList();
		
	}
}
