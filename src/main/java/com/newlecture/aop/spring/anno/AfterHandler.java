package com.newlecture.aop.spring.anno;

import java.lang.reflect.Method;

import org.springframework.aop.AfterReturningAdvice;

public class AfterHandler implements AfterReturningAdvice {

	@Override
	public void afterReturning(Object returnValue, Method method, Object[] args, Object target) throws Throwable {
		//���ǰ˻� / ��ȯ�� Ȯ��
		//��ȯ ���� ������ ������ ó���ϰų� �� �� ���Ǵ� ���� : After
		int result = (Integer) returnValue;
		if(result < 0)
			System.out.println("������ ��ȯ�Ͽ����ϴ�.");
		else
			System.out.println("����� ��ȯ�Ͽ����ϴ�");
		
			
	}

}