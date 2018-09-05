package com.newlecture.aop.spring;

import org.springframework.aop.ThrowsAdvice;

public class ErrorHandler implements ThrowsAdvice {
	
	/*
	 * ������ ó���ϴ� �ڵ带 �ۼ��մϴ�.
	 * �츮�� � ���ܰ� �߻��� �� �ִµ���..
	 * ���? target���� �޼ҵ带 ȣ���ϴٰ�.
	 * ���⼭�� �� ���ܸ� �޴� ���Դϴ�.
	 * �� ���ܸ� �޾Ƽ� ó���ϴ� ���Դϴ�.
	 * �� ���� ó���ϴ� ������ ����ȭ�ϴ� ���.
	 */
	
	//�Ű� ������ ���� ó���� �� �ִ� ������ �޶���.
	public void afterThrowing(ArithmeticException e) throws Throwable {
		System.out.println("����. ����ġ ���� ������ �߻��Ͽ����ϴ�.");
	}
	
	public void afterThrowing(IllegalArgumentException e) throws Throwable {
		System.out.println("I ����");
	}
}