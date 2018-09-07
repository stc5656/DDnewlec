package com.newlecture.webapp.config;

import javax.servlet.Filter;

import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

// �Ʒ� �������� ����ü
// web.xml���� ����ó������ ������ �κ��̴�. ��� ����
// �׷��� ���⼭ �ؾ��� �⺻���� ������

// 1. URL ���� �����ϱ�
/*<servlet-mapping>
	<servlet-name>dispatcher</servlet-name>
	<url-pattern>/</url-pattern>
</servlet-mapping>*/

// 2. ������ ���� ������ �̿��ϱ�
// ����, ����, ���� - contextconfigloglocation

// 3. ���� ���� ��) ���ڵ� / ���� ���..

// 4. ���� ���� ����Ʈ

// 5. ��Ÿ ���


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
		// ���� ������ ����ϱ� ���� �ڹ� Ŭ������ �������� �Ѵ�.
		// ServletContextConfig{} ���� �����Ͽ� ��Ű��Ű??
		
		
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
