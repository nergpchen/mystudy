package com.mystudy.example.spring.ioc;

import java.util.HashMap;
import java.util.Map;

/**
 * 实现自己的IOC类:
 * IOC容器主要是用来生成对象和管理对象间的关系：
 * 比如在一个A类中，如果你需要B类的话，则可以从IOC容器获取,所以需要一个HashMap来存储对象
 * 在Spring中的代码
 *  ApplicationContext context = new FileSystemXmlApplicationContext(   
                "applicationContext.xml");   
        Animal animal = (Animal) context.getBean("animal");   
        
 * @author Administrator
 *
 */
public class MyIocImpl {

	Map<String,Object> beans = new HashMap<String,Object>();
	
	public Object getBean(String name){
		 if(name == null || name.equals(""))
			 return null;
		 Object o = beans.get(name);
		 if(o != null)
			 return o;
		 //如果没有，则先生成一个
		 try {
			Class<?> class1 = Class.forName(name);
			return class1.newInstance();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 return null;
	}
}
