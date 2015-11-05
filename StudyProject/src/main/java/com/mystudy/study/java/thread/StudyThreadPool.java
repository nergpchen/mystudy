package com.mystudy.study.java.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**问题：为什么线程的开销会大呢？
 * https://en.wikipedia.org/wiki/Thread_pool_pattern
 * http://my.oschina.net/zouqun/blog/407149
 * http://shmilyaw-hotmail-com.iteye.com/blog/1897638
 * http://www.cnblogs.com/coser/archive/2012/03/10/2389264.html
 * http://www.cnblogs.com/dolphin0520/p/3932921.html
 * http://ju.outofmemory.cn/entry/96003
 * http://www.cnblogs.com/cstar/archive/2012/06/14/2549494.html
 * http://dongxuan.iteye.com/blog/902571
 * http://www.cnblogs.com/dolphin0520/p/3932921.html
 * java线程池的学习大纲:
 * 线程池的基本知识：什么是线程.线程的属性.线程的特点.线程池的框架组成.
 * java线程池的实现：怎么创建一个线程池？怎么添加一个任务？
 * 
 * 1:什么是线程池?
 * 2:内部结构是什么？
 * 3:线程池解决了什么问题？
 * 2:线程池如何使用？
 * 3：线程池如何实现？
 * 4：一些技术难点是什么?
 * 5:线程的内部结构：线程如何初始化？线程的状态怎么样？状态间怎么切换？线程怎么排队的？线程怎么拒绝的？线程怎么关闭的？线程容量的动态调整
 * 6:如果你用的是固定的线程池，此时线程池已经满了，但是现场请求非常多的情况下，系统处理不过来这个时候应该怎么处理呢？
   java针对这种情况提供了拒绝服务，提供了4种的拒绝策略 .java 提供了RejectedExcutionHandler接口用来实现拒绝服务。
        在ThreadPoolExecutor中已经提供了四种处理策略。
  1:AbortPolicy :不处理，直接抛出RejectedExecutionException异常
  2:CallerRunsPolicy :如果线程池没有关闭的化，则直接运行提交的任务，不交由线程池来运行。
  3:DiscardOldestPolicy: 如果线程池没有关闭的话，则第一个任务将被删除，然后重新执行该任务。

 public void rejectedExecution(Runnable r, ThreadPoolExecutor e) {
            if (!e.isShutdown()) {
                e.getQueue().poll();
                e.execute(r);
            }
        }

4:DiscardPolicy :不处理，也不抛出异常。

1：线程池状态：4种状态
	static final int RUNNING    = 0; 刚创建线程池的时候
	static final int SHUTDOWN   = 1;调用了shutdown()方法
	static final int STOP       = 2;调用shutdonwNow()方法
	static final int TERMINATED = 3;当处于shotwond或者stop方法，并且所有的线程工作已经销毁,任务缓存队列已经清空或者执行结束后，则是这个状态.
2：执行的过程：
   从提交到最后都经历了哪些过程。

public void execute(Runnable command) {
    if (command == null)
        throw new NullPointerException();
    if (poolSize >= corePoolSize || !addIfUnderCorePoolSize(command)) {
        if (runState == RUNNING && workQueue.offer(command)) {
            if (runState != RUNNING || poolSize == 0)
                ensureQueuedTaskHandled(command);
        }
        else if (!addIfUnderMaximumPoolSize(command))
            reject(command); // is shutdown or saturated
    }
}
3:线程池的初始化过程：
4:任务缓存队列及排队策略
在前面我们多次提到了任务缓存队列，即workQueue，它用来存放等待执行的任务。

　　workQueue的类型为BlockingQueue<Runnable>，通常可以取下面三种类型：

　　1）ArrayBlockingQueue：基于数组的先进先出队列，此队列创建时必须指定大小；

　　2）LinkedBlockingQueue：基于链表的先进先出队列，如果创建时没有指定此队列大小，则默认为Integer.MAX_VALUE；

　　3）synchronousQueue：这个队列比较特殊，它不会保存提交的任务，而是将直接新建一个线程来执行新来的任务。
5.线程池的关闭
  　　ThreadPoolExecutor提供了两个方法，用于线程池的关闭，分别是shutdown()和shutdownNow()，其中：
	shutdown()：不会立即终止线程池，而是要等所有任务缓存队列中的任务都执行完后才终止，但再也不会接受新的任务
	shutdownNow()：立即终止线程池，并尝试打断正在执行的任务，并且清空任务缓存队列，返回尚未执行的任务
6. 线程池容量的动态调整
可以通过调用setCorePoolSize()和setMaximumPoolSize() 来动态调整线程池容量大小


 * @author Administrator
 *
 */

public class StudyThreadPool {

	/**
	 * 线程池的基本知识：
	 * 是什么：线程池就是线程的容器，在这个容器中存放了多个线程，需要使用的时候从线程取，而不用自己创建。
	        如果线程的创建和销毁都需要消耗资源，所以利用线程池技术，预先先创建一批线程存放到容器中，如果需要用的时候就从容器中获取，而不用自己创建。
	   特点：     
	        线程池是预先创建线程的一种技术。
	        线程池在还没有任务到来之前，创建一定数量的线程，放入空闲队列中。这些线程都是处于睡眠状态，即均为启动，不消耗CPU，而只是占用较小的内存空间。
	        当请求到来之后，缓冲池给这次请求分配一个空闲线程，把请求传入此线程中运行，进行处理。
	        当预先创建的线程都处于运行状态，即预制线程不够，线程池可以自由创建一定数量的新线程，用于处理更多的请求。
	        当系统比较闲的时候，也可以通过移除一部分一直处于停用状态的线程。
	  线程池的注意事项

		虽然线程池是构建多线程应用程序的强大机制，但使用它并不是没有风险的。在使用线程池时需注意线程池大小与性能的关系，注意并发风险、死锁、资源不足和线程泄漏等问题。

	（1）线程池大小。多线程应用并非线程越多越好，需要根据系统运行的软硬件环境以及应用本身的特点决定线程池的大小。一般来说，如果代码结构合理的话，线程数目与CPU 数量相适合即可。如果线程运行时可能出现阻塞现象，可相应增加池的大小；如有必要可采用自适应算法来动态调整线程池的大小，以提高CPU 的有效利用率和系统的整体性能。

	（2）并发错误。多线程应用要特别注意并发错误，要从逻辑上保证程序的正确性，注意避免死锁现象的发生。

	（3）线程泄漏。这是线程池应用中一个严重的问题，当任务执行完毕而线程没能返回池中就会发生线程泄漏现象   
     内部结构：
        一个典型的线程池，应该包括如下几个部分：
	1、线程池管理器（ThreadPool），用于启动、停用，管理线程池
	2、工作线程（WorkThread），线程池中的线程
	3、请求接口（WorkRequest），创建请求对象，以供工作线程调度任务的执行
	4、请求队列（RequestQueue）,用于存放和提取请求
	5、结果队列（ResultQueue）,用于存储请求执行后返回的结果
	java的线程池：
	1: 线程池的  状态 ：
	2: 任务的执行
	3: 线程池中的初始化
	4 :
	
	状态：private static final int RUNNING    = -1 << COUNT_BITS;
*/
	
	public void baseInfo(){
		System.out.println("介绍线程池是什么，线程池的特点，线程池的内部结构");
	}
	
	/**http://ifeve.com/java-threadpool/
	 * 如何创建线程池
	 * java中如何使用线程池呢？
	 * 使用ThreadPoolExecutor来创建一个线程池
	 * new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
     在java中
                 这个线程池技术:
	 */
	public void howToCreateThreadPool(){
		
		ExecutorService services = Executors.newCachedThreadPool();
		
		ExecutorService services2 = new ThreadPoolExecutor(0, Integer.MAX_VALUE,
                60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
	}
	/**
	 *1:如何创建线程：
	 *2：如何传人任务到线程中
	 *3：如何关闭线程
	 * 使用线程池，我们把任务传入到一个线程池中
	 */
	public void howToUserTheadPool(){
		ExecutorService services = Executors.newCachedThreadPool();
		//运行一个任务
        services.execute(new Runnable(){

			public void run() {
				System.out.println("创建一个线程到线程池中");
				
			}
        	
        });
        Future<?> result =  services.submit(new Runnable(){

			public void run() {
				System.out.println("提交一个线程 ,返回一个结果");
				
			}
        });
        
        //关闭线程池
        services.shutdown();
	}
	/**
	 * 学习java线程池的原理。
	 * java提供了ThreadPoolExecutor来实现线程池,那么我们来看看ThreadPoolExecutor如何实现的，提供了哪些方法和成员来实现一个线程池？
	 * 如何管理线程池，如何实现线程池的.
	 * 线程池的内部结构：
	 * 怎么关闭线程池？
	 * 怎么传人一个线程？
	 */
	public void thePrinciple(){
		
	}
}
