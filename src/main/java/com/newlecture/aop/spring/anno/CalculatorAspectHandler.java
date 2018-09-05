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
		System.out.println("권한 확인");		
	}
	
	// private, public 상관없으면 * 씀
	@AfterReturning(pointcut="execution(* *..NewlecCalculator.add(..))", returning="returnValue")
	public void after(JoinPoint joinPoint, Object returnValue) {
		
		int result = (Integer) returnValue;
		if(result < 0)
			System.out.println("음수를 반환하였습니다.");
	}
	
	
	@AfterThrowing(pointcut="execution(* *..NewlecCalculator.div(..))", throwing="e")
	public void errorHandler(JoinPoint joinPoint, Throwable e) {
		System.out.println("ㅈㅅ. 예기치 않은 오류("+e.getMessage()+")가 발생하였습니다.");
	}
	
	
	@Around("execution(* *..NewlecCalculator.add(..))")
	public Object logHandler(ProceedingJoinPoint joinPoint) {
		//long start = System.currentTimeMillis();
		//스프링이 제공하는 StopWatch
		StopWatch watch = new StopWatch();
		watch.start();
				
				
		//Object result = method.invoke(cal, args); 인자를 넘겨 줄 필요가 없음.
		//바로 실행해랏
		
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
				
		String message = duration+"ms가 걸림";
		//String message = (end-start)+"ms가 걸림";
		System.out.println(message);		        
		        
		return result;	
		
		
	}
	
	
}
