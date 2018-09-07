package com.newlecture.webapp.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.savedrequest.SavedRequest;
import org.springframework.stereotype.Component;

import com.newlecture.webapp.service.MybatisHomeService;

@Component
public class NewlectureAuthenticationSuccessHandler implements AuthenticationSuccessHandler{

	/*
	 * User -> UI -> DB
	 * User -> UI -> DAO -> DB
	 * User -> UI -> Service -> DAO -> DB
	 * 
	 */
	@Autowired
	private MybatisHomeService service;
	
	//객체수를 줄이겡 위에다 넣으면 NewlectureAuthenticationSuccessHandler : redirectStrategy 일대일
	RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request
			, HttpServletResponse response
			, Authentication auth)
			throws IOException, ServletException {
			

		/*---------- 인터럽트 된 상태에서 로그인 처리 방법 ------------------------*/
		HttpSession session = request.getSession();
		SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
		if(savedRequest != null) {
			String returnURL = savedRequest.getRedirectUrl();
			redirectStrategy.sendRedirect(request, response, returnURL);
			
			return; //밑에 꺼 실행 노노
		}
		
		
		/*---------- 인터럽트 되지 않은 로그인 처리 방법 ------------------------*/
		// 서비스 
		//?.getDefaultRoleName(memberId);
		
		// Dao [CRUD]에 초점
		//.get
		
		
		String memberId = auth.getName();
		String roleName = service.getDefaultRoleName(memberId);
		
		switch (roleName) {
		case "ROLE_TEACHER":
			redirectStrategy.sendRedirect(request, response, "/student/index");
			break;
		case "ROLE_ADMIN":
			redirectStrategy.sendRedirect(request, response, "/admin/index");
			break;
		default:
			redirectStrategy.sendRedirect(request, response, "/student/index");
			break;
		}
		
		
		
		
	}

}