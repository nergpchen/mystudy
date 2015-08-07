package example.task;

import java.util.Timer;
import java.util.TimerTask;

/**学习资料：http://man.lupaworld.com/content/develop/open-open/14.htm
 * 任务调度学习。
 * java如何实现任务调度？什么是任务调度？简单的理解就是我们可以把一个线程Thread当做是任务，如果有100个任务，那么我们如何来管理这些任务呢？
 * 什么时候执行？什么时候暂停？什么时候中断？什么时候停止？什么时候清理等这些动作,
 * java提供了 java.util.Timer 这个类来处理.
 * 那么这个类是如何实现的？怎么操作这个类呢？有什么优点和缺点呢？
 * 有没有其他的方法呢？
 * 适用场景是哪些呢？
 * 原理是哪些呢？
 * 原理：
 * 看代码主要是通过Timer.sckedule()来执行的，我们只需要定义好具体的任务，通过Timer.sckedule(),则Timer会自动来执行任务
 * Timer类学习({@code Timer})
 * 
 * @author cx
 *
 */
public class MyTimerTask extends TimerTask {

	String taskName = "" ;
	
	public MyTimerTask(String taskName){
		super();
		this.taskName = taskName;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("学习任务调度");
	}
	public static void main(String[] args) {
		MyTimerTask task  = new MyTimerTask("test");
		Timer timer = new Timer();
		timer.schedule(task, 0);
		
	}

}
