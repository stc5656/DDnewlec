package com.newlecture.webapp.dao;

import java.util.List;

import com.newlecture.webapp.entity.Subject;

public interface SubjectDao {
	
	int insert(Subject subject);
	int delete(long id);
	int update(Subject subject);
	
	Subject get(long id);
	List<Subject> getList();	

}
