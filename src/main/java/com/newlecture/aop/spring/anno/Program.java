package com.newlecture.aop.spring.anno;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newlecture.aop.spring.Calculator;

public class Program {

	public static void main(String[] args) {
		
		/*
		cal ��ü ����
		proxy ����
		�� ���� ���� �ڵ带 ���� ��İ� �ٸ���
		�� �κ��� �������� �����ϰ� [spring-context�� �̿�]
		�������� ���� ��ü(target)�� ����
	    �ƴϸ� �����ø� ���� ���⼭�� �� �� ����.
		*/
		
		//Calculator cal = ?;
		
		ApplicationContext context = new ClassPathXmlApplicationContext("com/newlecture/aop/spring/anno/spring-context.xml");
		
		Calculator cal = (Calculator) context.getBean("cal");
		
		int result = cal.add(3, 6);
		
		System.out.println("3-10 :" + result);

	}

}