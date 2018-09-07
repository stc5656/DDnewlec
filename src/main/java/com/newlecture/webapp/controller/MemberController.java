package com.newlecture.webapp.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

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
		// �� ������
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
	public String joinEmail(String email, HttpServletResponse response) {

		// 인증메일 보내는거..	(sadfsdfsafdsa243141dsfg 이런거)	
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;

		try {

			salt = MessageDigest.getInstance("SHA-256"); // 암호화 기법 중에 하나
			salt.update(uuid.toString().getBytes());

			byte[] key = salt.digest();

			// 바이트열을 문자열로 바꾸기 위해서 더하기가 반복되어야 한다.
			StringBuilder builder = new StringBuilder();

			for (byte b : key)
				builder.append(String.format("%02x", b));

			digest = builder.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}

		/* uuid.toString(); */

		System.out.println(email);
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;

		System.out.println(uuid);
		System.out.println(digest);
		System.out.println(email);
		
		Cookie cookie = new Cookie("joinId", digest);
		cookie.setPath("/member/"); // 경로는 무슨 용도냐??
		cookie.setMaxAge(60*60*24); //단위;?		
		
		response.addCookie(cookie);		
		
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("회원가입을 위한 인증메일");
			helper.setText("<a href=\"http://211.238.142.36:8080/member/join-reg?id="+digest+"&em="+email+"\">가입링크</a>", true);

		} catch (MessagingException e) {

			e.printStackTrace();

		}
		
		// 이것만 새로 세팅하고 위에꺼는 그대로 두면됨!!
		/*mailSender.send(message);*/

		return "member.join-email-info";
	}

	/*------------------------------------------*/

	@GetMapping("join-reg")
	public String joinReg(@RequestParam(value="id", defaultValue="") String key
			, @RequestParam(value="em", defaultValue="") String email
			, @CookieValue(value="joinId", defaultValue="") String joinId
			, Model model) {		
		
		// 이메일 인증 과정 중 오류 발생
		if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "member.join-error";
		
		String uid = email.substring(email.lastIndexOf("@")+1);  // newlec@namoolab.com 에서 앞에 newlec만 발췌하는 코드
				
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
				
		return "member.join-reg";		
	}
	
	@GetMapping("login")
	public String login() {

		return "member.login";
	}

}
