package com.newlecture.webapp.controller;

import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.mail.javamail.JavaMailSender;

import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;


import com.newlecture.webapp.entity.Member;
import com.newlecture.webapp.service.MybatisHomeService;


@Controller
@RequestMapping("/member/")
public class MemberController {
	
	
	// 서비스를 왜 만드냐?? 책임자다.
	// 원래는 시스템을 기반으로 하지만 디렉토리 기반으로 했다.
	@Autowired
	private MybatisHomeService service;
	
	/*@Autowired
	private MemberDao memberDao;*/

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("join")
	public String join(Model model) {

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
		
				
		boolean duplicated = service.ischeckEmailDuplicated(email);
		
		// 동사형으로 이름 지어..
		// 이미 가입된 내역이 있다는 문구 출력
		
		if(duplicated)
			return "redirect:email-duplicated-error";
		
		
		
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
	
	// 컨트롤러
	@GetMapping("is-id-duplicated")
	@ResponseBody
	public String isIdDuplicated(String id) {	
		
		boolean duplicated = service.isIdDuplicated(id);
		
		if(duplicated)
			return "true";
		
		return "false";		
	}
	
	@GetMapping("email-duplicated-error")
	@ResponseBody
	public String emailDuplicatedError(HttpServletResponse response) {
		// location.href='join-email' 이메일 요청하는거
		// 한글 깨지는 현상 해결 방법 : HttpServletResponse response 써줘야 안깨짐
		
		return "<script>alert('이미 가입된 이메일 입니다.'); location.href='join-email';</script>";
	}
	
	/*------------------------------------------*/

	@GetMapping("join-reg")
	public String joinReg(@RequestParam(value="id", defaultValue="") String key
			, @RequestParam(value="em", defaultValue="") String email
			, @CookieValue(value="joinId", defaultValue="") String joinId
			, Model model) {		
		
		// 이메일 인증 과정 중 오류 발생
		/*if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "member.join-error";*/
		
		// 문자열 자르기
		/*String uid = email.substring(email.lastIndexOf("@")+1); */ // newlec@namoolab.com 에서 앞에 newlec만 발췌하는 코드
		String uid = email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
				
		return "member.join-reg";		
	}
	
	// 포스트한 데이터를 담아두는 
	@PostMapping("join-reg")
	public String joinReg(
			Member member, 
			@RequestParam("photo-file") MultipartFile photoFile,
			HttpServletRequest request) throws IOException{
				
		String resLocation = "/resources/users/newlec/";
		
		/*// 사용자가 많을 경우 이렇게.. 계정명 넣어서.. 다른 사람이 저장하게 되면 이런 형식으로 저장됨
		// "/resources/users/newlec/photo1.jpg" 
		// windows -> "d:\home\www\ROOT\resource\\user\newlec"
		// unix -> "/var/local/web/resource/user/newlec"		 	*/
		
		// 사진 파일 저장할때
		ServletContext context = request.getServletContext();
		String homeDir = context.getRealPath(resLocation);
		String uploadedFileName = photoFile.getOriginalFilename();
		String filePath = homeDir /*+ File.separator */+ uploadedFileName;
		
		System.out.println(filePath);		
		
		File dir = new File(homeDir);
		if(!dir.exists())
			dir.mkdir();
		
		InputStream fis = photoFile.getInputStream();
		FileOutputStream fos = new FileOutputStream(filePath);
		
		// fis에서 읽어서 fos 으로 복사하기
				
		 /*int data = 0;
		 while((data=fis.read())!=-1) {
		    fos.write(data);
		  }*/
		
		byte[] buf = new byte[1024];
		int size = 0;
		
		while((size = fis.read(buf, 0, buf.length)) != -1);
			fos.write(buf, 0, size);
		   
		 fis.close();
		 fos.close();		
		 
		 
		 // 저장하거는
		 PasswordEncoder encoder = new BCryptPasswordEncoder();
		 String encodedPwd = encoder.encode(member.getPwd());
		 
		 member.setPhoto(uploadedFileName);
		 member.setPwd(encodedPwd);
		 
		 service.insertMember(member);	 		
				 
		return "redirect:../index";				
	}
	
	@GetMapping("login")
	public String login() {

		return "member.login";
	}

}
