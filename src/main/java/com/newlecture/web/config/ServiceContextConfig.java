package com.newlecture.web.config;

import java.util.Properties;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

// �̰� ���Ǳ״�!! ��� �˷��ִ°�

@Configuration
@ComponentScan(basePackages= {"com.newlecture.webapp.dao.mybatis", "com.newlecture.webapp.service"})
public class ServiceContextConfig {
	
	// 보따리에 담는거..
	@Bean
	public BasicDataSource dataSource() {
		
		BasicDataSource dataSource = new BasicDataSource();
		
		
		//MS SQL SERVER ������ ����
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://211.238.142.251:1433;databaseName=lecture");
		dataSource.setUsername("sist");
		dataSource.setPassword("dclass");
		
		
		// MY SQL OR MARIADB
		//dataSource.setDriverClassName("com.mysql.jdbc.Driver");
		//dataSource.setUrl("jdbc:mysql://mb.coworkline.com/newlecturedb?autoReconnect=true&useSSL=false&useUnicode=true&amp;characterEncoding=utf8\"/");
		//dataSource.setUsername("mb");
		//dataSource.setPassword("111");	
		
		
		
		/* PULL ����*/
		dataSource.setRemoveAbandoned(true);
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		
		return dataSource;
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource dataSource) throws Exception{
		
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		
		factoryBean.setDataSource(dataSource);
		factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver()
				.getResources("classpath:com/newlecture/webapp/dao/mybatis/mapper/*.xml"));
		
		return factoryBean.getObject();	
		
	}
	
	@Bean
	public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory) {
		return new SqlSessionTemplate(sqlSessionFactory);
				
	}
	
	@Bean
	public JavaMailSender mailSender(){
	
	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	mailSender.setDefaultEncoding("UTF-8");
	mailSender.setHost("smtp.aaa.com");
	mailSender.setPort(333);
	mailSender.setUsername("aa@aaa.com");
	mailSender.setPassword("111");
	
	Properties javaMailProperties = new Properties();
	javaMailProperties.put("mail.transport", "smtp");
	javaMailProperties.put("mail.smtp.auth", true);
	javaMailProperties.put("mail.smtp.starttls.enable", true);
	javaMailProperties.put("mail.debug", true);
	mailSender.setJavaMailProperties(javaMailProperties);
	
	return mailSender;		
		
	}
}
