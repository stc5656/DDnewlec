package com.newlecture.webapp.dao;

import java.util.List;

import com.newlecture.webapp.entity.Question;
import com.newlecture.webapp.entity.Subject;

public interface QuestionDao {

	
	int insert(Question question);
	int delete(long id);
	int update(Question question);
	
	Question get(long id);
	List<Question> getList();	
}
