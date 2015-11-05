package com.mystudy.example.spring.aop;

import org.springframework.stereotype.Component;

/**
 * 这是一个业务类
 * @author Administrator
 *
 */
@Component
public class SystemLogImpl implements SystemLog {

	public void log() {
		// TODO Auto-generated method stub
		System.out.println("开始日志");
	}

}
