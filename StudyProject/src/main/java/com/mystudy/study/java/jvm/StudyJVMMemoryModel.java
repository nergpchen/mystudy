package com.mystudy.study.java.jvm;
/**
 * 引用资料：
 * 1:https://en.wikipedia.org/wiki/Java_memory_model
 * 2:http://ifeve.com/memory-model/
 * 3:http://www.oracle.com/technetwork/java/javase/memorymanagement-whitepaper-150215.pdf
 * 4:http://www.cnblogs.com/nexiyi/p/java_memory_model_and_thread.html
 * 学习JVM虚拟机内存模型
 * 基本概念：
 * 1:什么是内存模型:内存模型是什么？内存模型不是什么？相似的概念是什么？
 * 2:特点是什么?
 * 3:组成结构：
 * 4:相关基本知识点有哪些？
 * 5:核心点：
 * 6：重点：
 * 7:场景使用：如何使用内存模型？内存模型需要注意的地方有哪些？
 * @author Administrator
 *
 */
public class StudyJVMMemoryModel {

	/**
	 * The Java memory model describes how threads in the Java programming language interact through memory. 
	 * Together with the description of single-threaded execution of code, the memory model provides the semantics of the Java programming language.
	 * java内存模型(JMM) 描述了java程序的线程如何通过内存来交互.也就是内存模型就是定义了一套用于各个线程如何来操作内存的算法和规则.
	 * 那么JMM中的线程内存通信是什么意思？
	 * JMM控制内存通信是什么意思？
	 * JMM如何控制内存通信？
	 * java内存模型特点：
	 * 1:　Java内存模型中规定了所有的变量都存储在主内存中，
	 * 2：  每条线程还有自己的工作内存（可以与前面将的处理器的高速缓存类比），
	 * 3：  线程的工作内存中保存了该线程使用到的变量到主内存副本拷贝，线程对变量的所有操作（读取、赋值）都必须在工作内存中进行，而不能直接读写主内存中的变量。
	 * 4: 不同线程之间无法直接访问对方工作内存中的变量，线程间变量值的传递均需要在主内存来完成.。
	 *  
	 */
	public void 什么是java内存模型(){
		
	}
	/**
	 * 
	 */
    public void java内存组成(){
    	
    }
}
