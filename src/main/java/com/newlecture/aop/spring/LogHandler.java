package com.newlecture.aop.spring;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.springframework.util.StopWatch;

public class LogHandler implements MethodInterceptor{

	@Override
	public Object invoke(MethodInvocation method) throws Throwable {
		
		//long start = System.currentTimeMillis();
		//�������� �����ϴ� StopWatch
		StopWatch watch = new StopWatch();
		watch.start();
		
		
    	//Object result = method.invoke(cal, args); ���ڸ� �Ѱ� �� �ʿ䰡 ����.
		//�ٷ� �����ض�
		Object result = method.proceed();
    	
        //long end = System.currentTimeMillis();
		watch.stop();
		long duration = watch.getTotalTimeMillis();
		
        String message = duration+"ms�� �ɸ�";
        //String message = (end-start)+"ms�� �ɸ�";
        System.out.println(message);
        
        
		return result;
	}

}