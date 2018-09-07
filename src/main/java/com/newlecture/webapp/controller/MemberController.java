package com.newlecture.webapp.controller;


import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;
	
	@Autowired
	private JavaMailSender mailSender;
		
	@GetMapping("join")
	public String join(Model model) {
			
		
		Member member = memberDao.get("jojo");
				
		model.addAttribute("member", member);
		//�� ������
		return "member.join";
	}
	
	@GetMapping("join-email")
	public String joinEmail() {
		
		return "member.join-email";			
	}
	
	/**
	 * @param email
	 * @return
	 */
	@PostMapping("join-email")
	public String joinEmail(String email) {
				
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;
		
		try {
		helper = new MimeMessageHelper(message,  true, "UTF-8");
		helper.setFrom("noreply@newlecture.com");
		helper.setTo(email);
		helper.setSubject("회원가입을 위한 인증메일");
		helper.setText("test");	
		
		}catch(MessagingException e) {
			
			e.printStackTrace();
			
		}
		
		// 이것만 새로 세팅하고 위에꺼는 그대로 두면됨!!
		mailSender.send(message);
		
		return "member.join-email-info";			
	}	
	
	/*------------------------------------------*/
	
	@GetMapping("login")
	public String login() {
		
		return "member.login";
	}
	
	
	
	
	
	
	
		
}
