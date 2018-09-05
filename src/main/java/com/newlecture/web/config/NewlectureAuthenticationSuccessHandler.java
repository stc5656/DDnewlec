package com.newlecture.web.config;

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

	// USER -> UI -> DB ���� �Ը�
	// USER -> UI -> DAO -> DB 
	// USER -> UI -> SERVICE -> DAO -> DB
	
	@Autowired
	private MybatisHomeService service;
	
	private RedirectStrategy redirectStrategy = new DefaultRedirectStrategy();
	
	@Autowired
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
			throws IOException, ServletException {
	/*--------���ͷ�Ʈ�� ���¿��� �α��� ó�� ���-------*/
	HttpSession session = request.getSession();
	SavedRequest savedRequest = (SavedRequest) session.getAttribute("SPRING_SECURITY_SAVED_REQUEST");
		
	if(savedRequest != null) {
		String returnURL = savedRequest.getRedirectUrl();
		redirectStrategy.sendRedirect(request, response, returnURL);
			
		return; 
	}	
		
	/*--------���ͷ�Ʈ ���� ���� �α��� ó�� ���-------*/
	String memberId = authentication.getName();
	String roleName = service.getDefaultRoleName(memberId);
	
	
	switch (roleName) {
	case "ROLE_TEACHER":
		
		redirectStrategy.sendRedirect(request, response, "/teacher/index");
		
		break;
	
	case "ROLE_ADMIN":
	
		redirectStrategy.sendRedirect(request, response, "/admin/index");
	
		break;		
		
	default:	
	
	redirectStrategy.sendRedirect(request, response, "/student/index");
	}	
		/*getSubjectList();
		getLevelList();
		getQuestionList(String query); �˻���, ����, �����ʵ�, ������
		
		getQuestionList(String query)
		getQuestionList(String query, int page)
		getQuestionList(String query, boolean all)
		getQuestionList(String query, boolean all, int page, String Field, int page)*/
		
		
	}

}
