package com.mystudy.example.spring.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 用@Aspect注释来定义一个切面：
 * 1：定义切入点来匹配哪些类和方法需要织入代码
 * 2：定义通知：在什么时候织入代码
 * 比如@Before是一个
 * 
 * @author Administrator
 *
 */
@Component
@Aspect
public class AspcectAdvice {

	/**
     * 这个注解指定切入点
     * 匹配需要切入的类和方法。重点是语法学习：
     * 表达式：execution表示式外，还有within、this、target、args等Pointcut表示式
     * 1）execution(* *(..))
	表示匹配所有方法
	2）execution(public * com. savage.service.UserService.*(..))
	表示匹配com.savage.server.UserService中所有的公有方法
	3）execution(* com.savage.server..*.*(..))
     */
    @Pointcut("execution(* *(..))")
    public void anyMethod() {
    }
    
    /**
     * 前置通知:拦截需要织入代码的方法
     * 
     * @param jp
     */
    @Before(value = "execution(* *(..))")
    public void doBefore(JoinPoint jp) {
        System.out.println("===========进入before advice============ \n");

        System.out.print("准备在" + jp.getTarget().getClass() + "对象上用");
        System.out.print(jp.getSignature().getName() + "方法进行对 '");

        System.out.println("要进入切入点方法了 \n");
    }
    
    /**
     * 后置通知
     * 
     * @param jp
     *            连接点
     * @param result
     *            返回值
     */
    @AfterReturning(value = "anyMethod()", returning = "result")
    public void doAfter(JoinPoint jp, String result) {
        System.out.println("==========进入after advice=========== \n");
        System.out.println("切入点方法执行完了 \n");

        System.out.print(jp.getTarget().getClass() + "对象上被");
        System.out.print(jp.getSignature().getName() + "方法删除了");
        System.out.print("只留下：" + result + "\n\n");
    }
    
    /**
     * 环绕通知
     * 
     * @param pjp
     *            连接点
     */
    @Around(value = "execution(* *(..))")
    public void doAround(ProceedingJoinPoint pjp) throws Throwable {
        System.out.println("===========进入around环绕方法！=========== \n");

        // 调用目标方法之前执行的动作
        System.out.println("调用方法之前: 执行！\n");

        // 调用方法的参数
        Object[] args = pjp.getArgs();
        // 调用的方法名
        String method = pjp.getSignature().getName();
        // 获取目标对象
        Object target = pjp.getTarget();
        // 执行完方法的返回值：调用proceed()方法，就会触发切入点方法执行
        Object result = pjp.proceed();

        System.out.println("调用方法结束：之后执行！\n");
    }

    /**
     * 异常通知
     * 
     * @param jp
     * @param e
     */
    @AfterThrowing(value = "execution(* *(..))", throwing = "e")
    public void doThrow(JoinPoint jp, Throwable e) {
        System.out.println("删除出错啦");
    }

}
