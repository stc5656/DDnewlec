package com.newlecture.webapp.controller.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.webapp.entity.Subject;
import com.newlecture.webapp.service.TeacherService;


@Controller("teacherHomecontroller")  // Ŭ������
@RequestMapping("/teacher/")
public class HomeController {
	
	@Autowired
	private TeacherService TeacherService;
	
	@GetMapping("index")
	public String index(Model model) {
		
		List<Subject> subjects = TeacherService.getSubjectList();
		model.addAttribute("Subject", subjects);
		
		/*subjects.toString();*/
		
		return "teacher.index";	// �갡 Ÿ����� Ȯ���ؾߵ�	
	}
}

