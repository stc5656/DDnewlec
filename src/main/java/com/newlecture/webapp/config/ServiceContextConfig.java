package com.newlecture.webapp.config;

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

@Configuration
@ComponentScan(basePackages= {"com.newlecture.webapp.dao.mybatis", "com.newlecture.webapp.service"})
public class ServiceContextConfig{
	
	@Bean
	public BasicDataSource dataSource() {
		BasicDataSource dataSource = new BasicDataSource();
		
		/* DB 설정 */
		dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dataSource.setUrl("jdbc:sqlserver://211.238.142.251:1433;databaseName=lecture");
		dataSource.setUsername("sist");
		dataSource.setPassword("dclass");
		
		/* PULL 관리*/
		dataSource.setRemoveAbandoned(true);
		dataSource.setInitialSize(20);
		dataSource.setMaxActive(30);
		
		//dataSource.setRemove
		
		
		
		return dataSource;
	}
	//sqlSessionFactory(BasicDataSource dataSource) IOC 보따리에서 찾앗!
	@Bean
	public SqlSessionFactory sqlSessionFactory(BasicDataSource dataSource) throws Exception {
		SqlSessionFactoryBean sqlSessionFactory = new SqlSessionFactoryBean();
		sqlSessionFactory.setDataSource(dataSource);
		sqlSessionFactory.setMapperLocations(
				new PathMatchingResourcePatternResolver()
					.getResources("classpath:com/newlecture/webapp/dao/mybatis/mapper/*.xml"));
		
		return sqlSessionFactory.getObject();
	}
	
	@Bean
	public SqlSessionTemplate sqlSession(SqlSessionFactory sqlSessionFactory) {
		//sqlSessionFactoryBean은 맞지만 팩토리 객체를 반환하기 때문에.
		//SqlSessionFactoryBean->SqlSessionFactory로 변경하고 SqlSessionFactory return은 .getObject를 해야함
		return new SqlSessionTemplate(sqlSessionFactory);
	}
	
	
	@Bean
	public JavaMailSender mailSender(){
	
	// 메일 서버 사용자 정보
	JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	mailSender.setDefaultEncoding("UTF-8");
	mailSender.setHost("smtp.gmail.com");
	mailSender.setPort(587);
	mailSender.setUsername("seonghyeon226@gmail.com");
	mailSender.setPassword("qhfka2486");
	
	
	// 메일 서버 환경 설정 정보
	Properties javaMailProperties = new Properties();
	javaMailProperties.put("mail.transport", "smtp");
	javaMailProperties.put("mail.smtp.auth", true);
	javaMailProperties.put("mail.smtp.starttls.enable", true);
	javaMailProperties.put("mail.debug", true);
	mailSender.setJavaMailProperties(javaMailProperties);
	
	return mailSender;		
		
	}
}
