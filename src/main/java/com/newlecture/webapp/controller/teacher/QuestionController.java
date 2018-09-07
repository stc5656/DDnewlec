package com.newlecture.webapp.controller.teacher;


import java.security.Principal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;


import com.newlecture.webapp.entity.Question;
	
@Controller
@RequestMapping("/teacher/question/")
public class QuestionController{
	
	
	/*
	 1. Dispatcher�� ����ϴ� Front Controller�� Spring���κ��� �����޾Ƽ� ����ϰ� ����.
	 	MVC model2 ������� /teacher/question/type URL�� �����غ��Ҵ�.
	 
	 2. view �� �����ؼ� type.jsp �� ��û�� ���Ҵ�.
	 
	 3. RequestMapping�� return �� ���ڿ��� ���̴� ����� �˾ƺ��Ҵ�.
	 	return �� ���ڿ��� ���̱� ���ؼ� ViewResolver�� �����Ͽ���.
	 
	 4. model�� ����ϴ� ����� �����ϱ� 
	 
	*/
	@RequestMapping("type")
	public String type(Model model, Principal principal) {
		
		System.out.printf("test","hello");
		model.addAttribute("test","Hello");
		
		return "teacher.question.type"; /*���������� ���ش�*/
	}
	
	
	// GET ��û
	// @RequestMapping(value="reg", method=RequestMethod.GET) - �̰͵��� 4.0 �߹ݱ����� ���..
	// ������ �Ʒ� ������� ��..
	
	@GetMapping("choice-reg")
	public String choiceReg() {
				
		return "teacher.question.choice-reg"; 
	}
	
	// POST ��û - 4.0 �߹ݱ����� ���..	
	// @RequestMapping(value="reg", method=RequestMethod.POST)
	
	
	@PostMapping("choice-reg")
	public String choiceReg(Question question) {		
		
		return "redirect:type"; 
	}
	
	@RequestMapping("admin")
	public String admin() {
						
		return "teacher.question.admin"; /*���������� ���ش�*/
	}
	
	
}
