package com.newlecture.web.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// 아래 세가지의 집합체
// web.xml에서 디스패처서블릿을 생성한 부분이다. 라고 생각
// 그래서 여기서 해야할 기본적인 설정은

// 1. URL 매핑 설정하기
/*<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>*/

// 2. 다음과 같은 설정들 이용하기
// 서블릿, 서비스, 보안 - contextconfigloglocation

// 3. 필터 설정 예) 인코딩 / 보안 등등..

// 4. 웰컴 파일 리스트

// 5. 기타 등등


public class DispatcherServletInitializer extends AbstractAnnotationConfigDispatcherServletInitializer{

	@Override
	protected Class<?>[] getRootConfigClasses() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected Class<?>[] getServletConfigClasses() {

		/*<init-param>
  		<param-name>contextConfigLocation</param-name>
  		<param-value>/WEB-INF/spring/servlet-context.xml</param-value>
  		</init-param>*/
		// 위의 설정을 대신하기 위한 자바 클래스를 다음으로 한다.
		// ServletContextConfig{} 에서 설정하요 오키도키??
		
		
		return new Class[] {
				ServletContextConfig.class,
				ServiceContextConfig.class,
				SecurityContextConfig.class
							/*,TilesConfig.class*/
				
		};
	}

	@Override
	protected String[] getServletMappings() {

		/*<servlet-mapping>
		<servlet-name>dispatcher</servlet-name>
		<url-pattern>/</url-pattern>
		</servlet-mapping>*/
		
		return new String[] {"/"};
		
	}	
	
	@Override
	protected Filter[] getServletFilters() {
		// TODO Auto-generated method stub
		return null;
		
	}	
	
	
}
