package com.newlecture.webapp.controller.teacher;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.newlecture.webapp.entity.Question;
	
@Controller
@RequestMapping("/teacher/question/")
public class QuestionController{
	
	
	/*
	 1. Dispatcher를 담당하는 Front Controller를 Spring으로부터 제공받아서 사용하고 있음.
	 	MVC model2 방식으로 /teacher/question/type URL을 구현해보았다.
	 
	 2. view 를 구현해서 type.jsp 를 요청해 보았다.
	 
	 3. RequestMapping과 return 뷰 문자열을 줄이는 방법을 알아보았다.
	 	return 뷰 문자열을 줄이기 위해서 ViewResolver를 설정하였다.
	 
	 4. model을 사용하는 방법을 이해하기 
	 
	*/
	@RequestMapping("type")
	public String type(Model model) {
				
		model.addAttribute("test","Hello");
		
		return "teacher.question.type"; /*페이지명을 써준다*/
	}
	
	
	// GET 요청
	// @RequestMapping(value="reg", method=RequestMethod.GET) - 이것들은 4.0 중반까지만 썼고..
	// 지금은 아래 방법으로 함..
	
	@GetMapping("choice-reg")
	public String choiceReg() {
				
		return "teacher.question.choice-reg"; 
	}
	
	// POST 요청 - 4.0 중반까지만 썼고..	
	// @RequestMapping(value="reg", method=RequestMethod.POST)
	
	
	@PostMapping("choice-reg")
	public String choiceReg(Question question) {		
		
		return "redirect:type"; 
	}
	
	@RequestMapping("admin")
	public String admin() {
						
		return "teacher.question.admin"; /*페이지명을 써준다*/
	}
	
	
}
