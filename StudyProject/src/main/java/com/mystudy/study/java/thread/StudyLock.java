package com.mystudy.study.java.thread;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.lang.Thread;

import org.junit.Test;

/**
 * 资料：http://www.cnblogs.com/dolphin0520/p/3923167.html
 * LOCK:java提供的新的并发机制，可以提供比原来的锁机制更多的功能。
 * 特点：
 * 注意点：
 * 1）Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问；

　　2）Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
   * 怎么使用呢？
 * @author Administrator
 *
 */
public class StudyLock {
	
	
	/**
	 * 学习
	 * 1：锁是什么？
	 * 2：锁的特点?
	 * 3:锁解决了什么问题？
	 * 4:锁和java同步关键词的区别
	 * 5:锁的体系结构:
	 * 	lock,ReentrantLock,ReadWriteLock,ReentrantReadWriteLock.
	 * 6:锁的用法是什么.
	 * 7:注意事项。
	 * 8:可以看些源码
	 * 锁的相关概念介绍
	 * 1:可重入锁
	 * 2:可中断锁
	 * 3:公平锁
	 * 4:4.读写锁
	 */
	public void studyWhatisLock(){
		
	}
	
	/**
	 * 　synchronized是java中的一个关键字，也就是说是Java语言内置的特性。那么为什么会出现Lock呢？

　　在上面一篇文章中，我们了解到如果一个代码块被synchronized修饰了，当一个线程获取了对应的锁，并执行该代码块时，其他线程便只能一直等待，等待获取锁的线程释放锁，而这里获取锁的线程释放锁只会有两种情况：

　　1）获取锁的线程执行完了该代码块，然后线程释放对锁的占有；

　　2）线程执行发生异常，此时JVM会让线程自动释放锁。

　　那么如果这个获取锁的线程由于要等待IO或者其他原因（比如调用sleep方法）被阻塞了，但是又没有释放锁，其他线程便只能干巴巴地等待，试想一下，这多么影响程序执行效率。

　　因此就需要有一种机制可以不让等待的线程一直无期限地等待下去（比如只等待一定的时间或者能够响应中断），通过Lock就可以办到。

　　再举个例子：当有多个线程读写文件时，读操作和写操作会发生冲突现象，写操作和写操作会发生冲突现象，但是读操作和读操作不会发生冲突现象。

　　但是采用synchronized关键字来实现同步的话，就会导致一个问题：

　　如果多个线程都只是进行读操作，所以当一个线程在进行读操作时，其他线程只能等待无法进行读操作。

　　因此就需要一种机制来使得多个线程都只是进行读操作时，线程之间不会发生冲突，通过Lock就可以办到。

　　另外，通过Lock可以知道线程有没有成功获取到锁。这个是synchronized无法办到的。

　　总结一下，也就是说Lock提供了比synchronized更多的功能。但是要注意以下几点：

　　1）Lock不是Java语言内置的，synchronized是Java语言的关键字，因此是内置特性。Lock是一个类，通过这个类可以实现同步访问；

　　2）Lock和synchronized有一点非常大的不同，采用synchronized不需要用户去手动释放锁，当synchronized方法或者synchronized代码块执行完之后，系统会自动让线程释放对锁的占用；而Lock则必须要用户去手动释放锁，如果没有主动释放锁，就有可能导致出现死锁现象。
	 */
	public void study特征Oflock(){
		
	}
	
	/**
	 * 学习Lock的API接口
	 */
	public void studyApiOfLock(){
		
	}
	
	/**
	 * 学习如何取使用Lock
	 * Lock接口：有下面五个方法
	 *  用来锁：void lock();
    	用来锁：void lockInterruptibly() throws InterruptedException;
    	用来锁：boolean tryLock();
    	用来锁：boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
    	释放锁:void unlock():　lockInterruptibly()方法比较特殊，当通过这个方法去获取锁时，如果线程正在等待获取锁，则这个线程能够响应中断，即中断线程的等待状态。也就使说，当两个线程同时通过lock.lockInterruptibly()想获取某个锁时，假若此时线程A获取到了锁，而线程B只有在等待，那么对线程B调用threadB.interrupt()方法能够中断线程B的等待过程
    	Condition newCondition();
    	
	 */
	public void studyHowToUseLock(){
		
	}

	@Test
	public void testLock(){
		final StudyLock lock =new StudyLock();
		new Thread(){
            public void run() {
            	lock.save(Thread.currentThread());
            };
        }.start();
         
        new Thread(){
        	public void run(){
        		lock.save(Thread.currentThread());	
        	}
        }.start();
	}
	
	@Test
	public void testTryLock(){
		Lock lock = new ReentrantLock();
		try{
			if(lock.tryLock()){
				System.out.println("获得了锁");
			}else{
				System.out.println("获得了锁失败");
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	//
	@Test
	public void testlockInterruptibly(){
		Lock lock = new ReentrantLock();
		try {
			
			lock.lockInterruptibly();
			
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			lock.unlock();
		}
	}
	
	public void save(Thread thread){
		 ArrayList<Integer> arrayList = new ArrayList<Integer>();
		 Lock lock = new ReentrantLock();    //注意这个地方
		 lock.lock();
		 try{
			 System.out.println(thread.getName()+"得到锁");
			 for(int i=0;i<5;i++) {
	                arrayList.add(i);
	            }
		 }catch(Exception e){
			 
		 }finally{
			 System.out.println(thread.getName()+"释放锁");
			 lock.unlock();
		 }
		 
	}

}

