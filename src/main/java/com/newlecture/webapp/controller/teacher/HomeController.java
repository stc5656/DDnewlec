package com.newlecture.webapp.controller.teacher;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.newlecture.webapp.entity.Subject;
import com.newlecture.webapp.service.TeacherService;


@Controller("teacherHomecontroller")  // 클래스명
@RequestMapping("/teacher/")
public class HomeController {
	
	@Autowired
	private TeacherService TeacherService;
	
	@GetMapping("index")
	public String index(Model model) {
		
		List<Subject> subjects = TeacherService.getSubjectList();
		model.addAttribute("Subject", subjects);
		
		subjects.toString();
		
		return "teacher.index";	// 얘가 타일즈라서 확인해야돼	
	}
}

