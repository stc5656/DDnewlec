package com.newlecture.webapp.controller;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.PageAttributes.MediaType;
import java.awt.image.BufferedImage;
import java.io.File;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.Provider.Service;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
	
	
	// �꽌鍮꾩뒪瑜� �솢 留뚮뱶�깘?? 梨낆엫�옄�떎.
	// �썝�옒�뒗 �떆�뒪�뀥�쓣 湲곕컲�쑝濡� �븯吏�留� �뵒�젆�넗由� 湲곕컲�쑝濡� �뻽�떎.
	@Autowired
	private MybatisHomeService service;
	
	/*@Autowired
	private MemberDao memberDao;*/

	@Autowired
	private JavaMailSender mailSender;

	@GetMapping("join")
	public String join(Model model) {

		// 占쏙옙 占쏙옙占쏙옙占쏙옙
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
		
		// �룞�궗�삎�쑝濡� �씠由� 吏��뼱..
		// �씠誘� 媛��엯�맂 �궡�뿭�씠 �엳�떎�뒗 臾멸뎄 異쒕젰
		
		if(duplicated)
			return "redirect:email-duplicated-error";
		
		
		
		// �씤利앸찓�씪 蹂대궡�뒗嫄�..	(sadfsdfsafdsa243141dsfg �씠�윴嫄�)	
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;

		try {

			salt = MessageDigest.getInstance("SHA-256"); // �븫�샇�솕 湲곕쾿 以묒뿉 �븯�굹
			salt.update(uuid.toString().getBytes());

			byte[] key = salt.digest();

			// 諛붿씠�듃�뿴�쓣 臾몄옄�뿴濡� 諛붽씀湲� �쐞�빐�꽌 �뜑�븯湲곌� 諛섎났�릺�뼱�빞 �븳�떎.
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
		cookie.setPath("/member/"); // 寃쎈줈�뒗 臾댁뒯 �슜�룄�깘??
		cookie.setMaxAge(60*60*24); //�떒�쐞;?		
		
		response.addCookie(cookie);		
		
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 회원가입을 위한 인증메일");
			helper.setText("<a href=\"http://211.238.142.36:8080/member/join-reg?id="+digest+"&em="+email+"\">媛��엯留곹겕</a>", true);

		} catch (MessagingException e) {

			e.printStackTrace();

		}
		
		// �씠寃껊쭔 �깉濡� �꽭�똿�븯怨� �쐞�뿉爰쇰뒗 洹몃�濡� �몢硫대맖!!
		/*mailSender.send(message);*/

		return "member.join-email-info";
	}
	
	// 而⑦듃濡ㅻ윭
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
		// location.href='join-email' �씠硫붿씪 �슂泥��븯�뒗嫄�
		// �븳湲� 源⑥��뒗 �쁽�긽 �빐寃� 諛⑸쾿 : HttpServletResponse response �뜥以섏빞 �븞源⑥쭚
		
		return "<script>alert('이메일이 잘못되었습니다.'); location.href='join-email';</script>";
	}
	
	/*------------------------------------------*/

	@GetMapping("join-reg")
	public String joinReg(@RequestParam(value="id", defaultValue="") String key
			, @RequestParam(value="em", defaultValue="") String email
			, @CookieValue(value="joinId", defaultValue="") String joinId
			, Model model) {		
		
		// �씠硫붿씪 �씤利� 怨쇱젙 以� �삤瑜� 諛쒖깮
		/*if(key.equals("") || joinId.equals("") || !key.equals(joinId))
			return "member.join-error";*/
		
		// 臾몄옄�뿴 �옄瑜닿린
		/*String uid = email.substring(email.lastIndexOf("@")+1); */ // newlec@namoolab.com �뿉�꽌 �븵�뿉 newlec留� 諛쒖톸�븯�뒗 肄붾뱶
		String uid = email.split("@")[0];
		
		model.addAttribute("uid", uid);
		model.addAttribute("email", email);
				
		return "member.join-reg";		
	}
	
	
	@GetMapping("join-invalid-error")
	@ResponseBody
	public String joinInvalidError(HttpServletResponse response) {
		// location.href='join-email' �씠硫붿씪 �슂泥��븯�뒗嫄�
		// �븳湲� 源⑥��뒗 �쁽�긽 �빐寃� 諛⑸쾿 : HttpServletResponse response �뜥以섏빞 �븞源⑥쭚
		
		return "<script>alert('계산식이 올바르지 않습니다.'); location.href='join-email';</script>";
	}
	
	
	// �룷�뒪�듃�븳 �뜲�씠�꽣瑜� �떞�븘�몢�뒗 
	@PostMapping("join-reg")
	public String joinReg(
			Member member, 
			@RequestParam("photo-file") MultipartFile photoFile,
			Integer moonjae, 
			HttpServletRequest request) throws IOException{
				
		
		HttpSession session = request.getSession();
		Integer moonjaeSaved = (Integer) session.getAttribute("moonjae");
		
		// 결과를 처리하기 전에 사용자가 입력한 문제가 정답이 아니면 보내온 정보가 더 필요없게 된다.
		if(moonjae != moonjaeSaved) // 유효하지 않은 값인 경우
			return "member.join-invalid-error";
		
		
		String resLocation = "/resources/users/newlec/";
		
		/*// �궗�슜�옄媛� 留롮쓣 寃쎌슦 �씠�젃寃�.. 怨꾩젙紐� �꽔�뼱�꽌.. �떎瑜� �궗�엺�씠 ���옣�븯寃� �릺硫� �씠�윴 �삎�떇�쑝濡� ���옣�맖
		// "/resources/users/newlec/photo1.jpg" 
		// windows -> "d:\home\www\ROOT\resource\\user\newlec"
		// unix -> "/var/local/web/resource/user/newlec"		 	*/
		
		// �궗吏� �뙆�씪 ���옣�븷�븣
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
		
		// fis�뿉�꽌 �씫�뼱�꽌 fos �쑝濡� 蹂듭궗�븯湲�
				
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
		 
		 
		 // ���옣�븯嫄곕뒗
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
	
	
	@GetMapping("moonjae.png")			
	public void moonjae(HttpSession session, HttpServletResponse response) throws IOException {
		
		// 랜덤 정수 구하기
		
		/*int x = (int)(Math.random()*10);
		int y = (int)(Math.random()*10);*/
		
		
		Random rand = new Random();
				
		int x = rand.nextInt(100);
		int y = rand.nextInt(10);
		String fmtString = String.format("%d+%d=", x, y);
		
		session.setAttribute("moonjae", x+y);
		
		BufferedImage img = new BufferedImage(60, 30, BufferedImage.TYPE_INT_ARGB);
		
		// 그림 그릴수있는거
		Graphics2D g = img.createGraphics();
		
		g.setFont(new Font("돋움", 0, 13));
		
		// 배경
		g.setColor(Color.white);
		
		// 색채워주는거
		g.fillRect(0, 0, 60, 30);
		
		// 글자
		g.setColor(Color.BLACK);
		
		// 위치
		g.drawString(fmtString, 5, 20);
	
		response.setContentType("image/png");
		
		// 이미지 뿌리는거
		ImageIO.write(img, "png", response.getOutputStream());			
		
	}	
	
	
	@GetMapping("reset-pwd")
	public String resetPwd() {
		
		
		
		return "member.reset-pwd";		
		
	}
	
	
	@PostMapping("reset-pwd")
	public String resetPwd(String id) {
		
		
		Member member = service.getMember(id);		
		String email = member.getEmail();
		
		/*---------------unique key generating-----------------------------------------*/
			
		UUID uuid = UUID.randomUUID();
		MessageDigest salt = null;
		String digest = null;

		try {

			salt = MessageDigest.getInstance("SHA-256"); // �븫�샇�솕 湲곕쾿 以묒뿉 �븯�굹
			salt.update(uuid.toString().getBytes());

			byte[] key = salt.digest();

			// 諛붿씠�듃�뿴�쓣 臾몄옄�뿴濡� 諛붽씀湲� �쐞�빐�꽌 �뜑�븯湲곌� 諛섎났�릺�뼱�빞 �븳�떎.
			StringBuilder builder = new StringBuilder();

			for (byte b : key)
				builder.append(String.format("%02x", b));

			digest = builder.toString();

		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();

		}
		
		
		
		/*---------------unique key generating-----------------------------------------*/
				
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper;

		System.out.println(uuid);
		System.out.println(digest);
		System.out.println(email);
			
		try {
			helper = new MimeMessageHelper(message, true, "UTF-8");
			helper.setFrom("noreply@newlecture.com");
			helper.setTo(email);
			helper.setSubject("뉴렉처 비밀번호 재설정을 위한 인증메일");
			helper.setText("<a href=\"http://211.238.142.36:8080/member/join-reg?id="+digest+"&em="+email+"\">비밀번호 인증메일</a>", true);

		} catch (MessagingException e) {

			e.printStackTrace();

		}
		
		
		return "redirect:";		
		
	}

}
