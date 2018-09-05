package com.newlecture.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;

@Controller
@RequestMapping("/member/")
public class MemberController {
	
	@Autowired
	private MemberDao memberDao;

	@GetMapping("join")
	public String join(Model model) {
		
		Member member = memberDao.get("jojo");
		
		
		model.addAttribute("member", member);
		//∫‰ ∆‰¿Ã¡ˆ
		return "member.join";
	}
	
	@GetMapping("login")
	public String login() {
		
		return "member.login";
	}
		
}
