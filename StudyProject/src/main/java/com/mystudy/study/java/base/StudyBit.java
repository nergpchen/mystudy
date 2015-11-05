package com.mystudy.study.java.base;

import org.junit.Test;

/**
 * 位运算:位运算是在程序中按照位也就是二进制的一元或者二元进行运算。通常位运算比乘法快，和加减的速度差不多。
 * 位运算符包括：
 * 位与 & : 当参与运算的位都为1的时候，结果位才为1.
 * 位或 | ： 只要对应的二个二进位有一个为1时，结果位就为1。
 * 异或 ^ : 当两对应的二进位相异时，结果为1
 * 左移 <<: 左移n位就是乘以2的n次方,高位丢弃，低位补0
 * 右移 >>：  右移n位就是除以2的n次方
 * 求反~ ：   对参与运算的数的各二进位按位求反
 * 
 * @author Administrator
 *
 */
public class StudyBit {
	
	@Test
	public void test(){
		System.out.print("1>>2=");
		System.out.println(1>>2);
		
		System.out.print("1<<2=");
		System.out.println(1<<2);
		
		System.out.print("1<<2=");
		System.out.println(-1<<2);
		
		System.out.print("1>>>2=");
		System.out.println(1>>>2);
		
		
		System.out.print("-1>>>2=");
		System.out.println(-1>>>2);
		
		
		System.out.print("1|1=");
		System.out.println(1|1);
		
		System.out.print("1|0=");
		System.out.println(1|0);
		
		System.out.print("0|0=");
		System.out.println(000000|0);
		
		System.out.print("1&1=");
		System.out.println(1&1);
		
		System.out.print("1^0=");
		System.out.println(1^0);
		
		System.out.print("0^0=");
		System.out.println(0^0);
		
		System.out.print("1^0=");
		System.out.println(1^0);
		
		System.out.print("1^1=");
		System.out.println(1^1);
		
		System.out.print("~0=");
		System.out.println(~00000);
		
		System.out.print("~1=");
		System.out.println(~1);
		
		System.out.print("~2=");
		System.out.println(~2);
		
		System.out.print  ("0x00000011=");
		System.out.println( 0x00000011);
		
		System.out.print  ("0x00000011=");
		System.out.println( Integer.toBinaryString(2));
		
	}
	
}
