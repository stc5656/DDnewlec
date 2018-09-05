package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.newlecture.webapp.dao.QuestionDao;
import com.newlecture.webapp.entity.Question;

@Repository
public class MybatisQuestionDao implements QuestionDao{
	
	@Autowired
	private SqlSession SqlSession;

	@Override
	public int insert(Question question) {
		
		QuestionDao questionDao = SqlSession.getMapper(QuestionDao.class);
		
		return questionDao.insert(question);
	}

	@Override
	public int delete(long id) {
				
		QuestionDao questionDao = SqlSession.getMapper(QuestionDao.class);		
				
		return questionDao.delete(id);
	}

	@Override
	public int update(Question question) {
		
		QuestionDao questionDao = SqlSession.getMapper(QuestionDao.class);	
		
		return questionDao.update(question);
	}

	@Override
	public Question get(long id) {
		
		QuestionDao questionDao = SqlSession.getMapper(QuestionDao.class);	
		
		return questionDao.get(id);
	}

	@Override
	public List<Question> getList() {
		
		QuestionDao questionDao = SqlSession.getMapper(QuestionDao.class);	
		
		return questionDao.getList();
	}
	
	
	
	
}
