package com.newlecture.webapp.service;

import java.util.List;

import com.newlecture.webapp.entity.Level;
import com.newlecture.webapp.entity.Question;
import com.newlecture.webapp.entity.Subject;

public interface TeacherService {
	
	List<Subject> getSubjectList();
	List<Level> getLevelList();
	List<Question> getQuestionList(); 
	List<Question> getQuestionList(String query);
	List<Question> getQuestionList(String query, int page);
	List<Question> getQuestionList(String query, boolean all, int page);
	List<Question> getQuestionList(String query, boolean all, String sortField, int page);

}
