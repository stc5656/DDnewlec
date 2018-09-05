package com.newlecture.web.config;

import org.apache.commons.dbcp.BasicDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;


@Configuration
@ComponentScan("com.newlecture.web.config")
@EnableWebSecurity
public class SecurityContextConfig extends WebSecurityConfigurerAdapter{
	
		// MVC Framework 
	
	
	@Autowired
	private BasicDataSource dataSource;
	
	@Autowired
	private AuthenticationSuccessHandler successHandler;
	
	// http �κ��� �������� ����
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER") // ����ڰ� �������� �� �ֱ� ������
				.antMatchers("/student/**").hasAnyRole("ADMUN, STUDENT") // �� ����ڿ� �°� ����
				.antMatchers("/customer/question").authenticated()
				.anyRequest().permitAll()
			.and()
			.formLogin()				
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")
				.defaultSuccessUrl("/index") // �α��� ���� �� ������ �̵�
			.successHandler(successHandler)
				.and()
			.logout()
				.logoutUrl("/member/logout")
				.logoutSuccessUrl("/index")
				
				
			;			
			
			/*.antMatchers("/admin/**").hasRole("ADMIN")
			.antMatchers("/academy/**").hasAnyRole("ADMIN, ACADEMY")
			.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER")
			.antMatchers("/student/**").hasAnyRole("ADMIN, STUDENT");*/
	}
	
	// manager �κ��� �������� ��
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select id, pwd password, 1 enable from Member where id = ?")
			.authoritiesByUsernameQuery("select memberId, roleName authority from MemberRole where memberId = ?")
			.passwordEncoder(new BCryptPasswordEncoder());		
		
		
		// 1. ���� ������ ���� ����
		// 2. ��ӵ� �������̽��� ������ ��������� ���� ��ü
				
		// UserBuilder user = User.builder();
		/*auth.inMemoryAuthentication()
			.withUser(user
					.username("seene")
					.password("111")
					.roles("ADMIN"))
			.withUser(user
					.username("jojo")
					.password("222")
					.roles("TEACHER")
					
					
		);*/
		
	}
	 
}
