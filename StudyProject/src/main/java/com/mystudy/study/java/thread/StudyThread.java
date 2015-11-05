package com.mystudy.study.java.thread;

/**
 * 
 * 这个学习线程的相关基本知识
 * http://www.cnblogs.com/riskyer/p/3263032.html
   http://blog.csdn.net/yake25/article/details/7522366
   http://my.oschina.net/flashsword/blog/114527
   http://wangleide414.iteye.com/blog/1669479
 * 相关资料：
 * 锁的理解:
 * 1:syncronized的理解：
 * 可以给对象或者方法或者方法里的代码块加锁。
 * 代码锁：当锁定代码块的时候，同一时刻只有一个线程执行这段代码。
 * 方法锁：一次只能有一个线程访问该方法，等该方法执行好，释放掉锁，其他线程才能执行该方法。
 * 对象锁：syncronized(object):如果一个线程进入对象锁后，那么其他线程在该对象的所有操作都不能进行的。
 * 适用场景：锁用来解决资源共享冲突的问题。
 * 2:
 * 
 * 线程是什么?
 * 多线程是什么？
 * 如何用线程编写？
 * 线程的知识点有哪些？ 
 * @author Administrator
 *
 */
public class StudyThread {
	/**
	 * 学习线程基本知识:
	 * 涉及到的外部相关：CPU调度
	 * 1:线程是什么：
	 *           线程是操作系统的概念,和进程的概念类似，如果理解了进程的话，那么就非常容易理解线程了。
	 * 2：线程内部结构： ID,线程ID,线程自己的资源，所属的进程，线程状态
	 *   线程状态:NEW,RUNNABLE,RUNNING,BLOCK,DEATH 状态.
	 *   线程如何阻塞：通过sleep(),wait(),join(),yield()方法都可以阻塞，还有锁阻塞,I/O阻塞
	 *            sleep()方法：让线程阻塞一段时间，但是不释放对资源的锁。一段时间后进入Runnable状态
	 *            wait():让现场阻塞，进入等待池，释放对资源的锁，通过notify()可以来唤醒.
	 *            yield():让出CPU,让线程阻塞，但是只能让同优先级的线程有执行的机会.
	 *            join():让线程阻塞，等待线程执行好开始执行.
	 *            notify():让等待池中的任何一个线程进入Runnable状态。
	 *    
	 */
	
	public void threadBaseInfo(){
		
	}
	
	/**
	 * 如何建立线程：
	 * 通过继承Thread类或者实现Runnable接口来实现一个线程类.
	 * 
	 */
	public void howToCreateThread(){
	
		Thread thread1 = new Thread(){
			@Override
			public void run(){
				System.out.println(" 通过覆盖Thread.run()方法来创建一个线程");
			}
		};
		Runnable thread2 = new Runnable(){

			public void run() {
				// TODO Auto-generated method stub
				System.out.println(" 通过实现Runnable接口的.run方法来创建一个线程");

			}
		};
	}
	
	public void howToUseThread(){
		
	}
	
	
}
