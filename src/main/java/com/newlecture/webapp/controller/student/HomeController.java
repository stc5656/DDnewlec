package com.newlecture.webapp.controller.student;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller("studentHomeController")
@RequestMapping("/student/")
public class HomeController {

	/*@Autowired
	private MemberDao memberDao;	*/
	
	
	@GetMapping("index")
	public String index() {
	
		return "student.index";		
	}

}
