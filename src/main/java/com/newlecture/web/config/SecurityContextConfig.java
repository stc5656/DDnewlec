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
	
	// http 부분은 이쪽으로 오고
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.authorizeRequests()
				.antMatchers("/teacher/**").hasAnyRole("ADMIN, TEACHER") // 사용자가 여러명일 수 있기 때문에
				.antMatchers("/student/**").hasAnyRole("ADMUN, STUDENT") // 각 사용자에 맞게 접속
				.antMatchers("/customer/question").authenticated()
				.anyRequest().permitAll()
			.and()
			.formLogin()				
				.loginPage("/member/login")
				.loginProcessingUrl("/member/login")
				.defaultSuccessUrl("/index") // 로그인 성공 시 페이지 이동
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
	
	// manager 부분이 이쪽으로 와
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {		
		
		auth.jdbcAuthentication()
			.dataSource(dataSource)
			.usersByUsernameQuery("select id, pwd password, 1 enable from Member where id = ?")
			.authoritiesByUsernameQuery("select memberId, roleName authority from MemberRole where memberId = ?")
			.passwordEncoder(new BCryptPasswordEncoder());		
		
		
		// 1. 내가 쿼리를 만들어서 제공
		// 2. 약속된 인터페이스로 구현된 사용자정보 제공 객체
				
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
