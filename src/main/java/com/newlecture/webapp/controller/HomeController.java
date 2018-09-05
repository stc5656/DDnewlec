package com.newlecture.webapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;

@Controller
@RequestMapping("/")
public class HomeController {

	
	/*@Autowired
	private MemberDao memberDao;*/	
	
	
	@GetMapping("index")
	public String index(Model model) {
	
		/*Member member = memberDao.get("jojo");
		
		model.addAttribute("member", member);*/
		
		return "home.index";		
	}
}
