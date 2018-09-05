package com.newlecture.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import com.newlecture.webapp.dao.LevelDao;
import com.newlecture.webapp.dao.QuestionDao;
import com.newlecture.webapp.dao.SubjectDao;
import com.newlecture.webapp.entity.Level;
import com.newlecture.webapp.entity.Question;
import com.newlecture.webapp.entity.Subject;

@Component // ioc 컨테이너에 담는거..
public class MybatisTeacherService implements TeacherService {
	
	@Autowired
	private SubjectDao subjectDao;
	
	@Autowired
	private LevelDao levelDao;
	
	@Autowired
	private QuestionDao questionDao;
	
		
	@Override
	public List<Subject> getSubjectList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Level> getLevelList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, int page) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Question> getQuestionList(String query, boolean all, String sortField, int page) {
		// TODO Auto-generated method stub
		return null;
	}

}
