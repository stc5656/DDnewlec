package com.newlecture.aop.spring.anno;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.util.StopWatch;


@Aspect
public class CalculatorAspectHandler {
	
	@Before("execution(* *..NewlecCalculator.add(..))")	
	public void authorHandler() {
		System.out.println("���� Ȯ��");		
	}
	
	// private, public ��������� * ��
	@AfterReturning(pointcut="execution(* *..NewlecCalculator.add(..))", returning="returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) {
		
		int result = (Integer) returnValue;
		if(result < 0)
			System.out.println("������ ��ȯ�Ͽ����ϴ�.");
	}
	
	
	@AfterThrowing(pointcut="execution(* *..NewlecCalculator.div(..))", throwing="e")
	public void errorHandler(JoinPoint joinPoint, Throwable e) {
		System.out.println("����. ����ġ ���� ����("+e.getMessage()+")�� �߻��Ͽ����ϴ�.");
	}
	
	
	@Around("execution(* *..NewlecCalculator.add(..))")
	public Object logHandler(ProceedingJoinPoint joinPoint) {
		//long start = System.currentTimeMillis();
		//�������� �����ϴ� StopWatch
		StopWatch watch = new StopWatch();
		watch.start();
				
				
		//Object result = method.invoke(cal, args); ���ڸ� �Ѱ� �� �ʿ䰡 ����.
		//�ٷ� �����ض�
		
		Object result = null;
		try {
			result = joinPoint.proceed();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		    	
		//long end = System.currentTimeMillis();
		watch.stop();
		long duration = watch.getTotalTimeMillis();
				
		String message = duration+"ms�� �ɸ�";
		//String message = (end-start)+"ms�� �ɸ�";
		System.out.println(message);		        
		        
		return result;	
		
		
	}
	
	
}
