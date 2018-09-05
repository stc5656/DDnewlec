package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.newlecture.webapp.dao.SubjectDao;
import com.newlecture.webapp.entity.Subject;

@Repository
public class MybatisSubjectDao implements SubjectDao{

	@Autowired
	private SqlSession SqlSession;

	@Override
	public int insert(Subject subject) {

		SubjectDao subjectDao = SqlSession.getMapper(SubjectDao.class);
					
		return subjectDao.insert(subject);
	}

	@Override
	public int delete(long id) {
		
		SubjectDao subjectDao = SqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.delete(id);
	}

	@Override
	public int update(Subject subject) {
		
		SubjectDao subjectDao = SqlSession.getMapper(SubjectDao.class);		
		
		return subjectDao.update(subject);
	}

	@Override
	public Subject get(long id) {
		
		SubjectDao subjectDao = SqlSession.getMapper(SubjectDao.class);		
		
		return subjectDao.get(id);
	}

	@Override
	public List<Subject> getList() {
		
		SubjectDao subjectDao = SqlSession.getMapper(SubjectDao.class);
		
		return subjectDao.getList();
	}
	
	
	
	
}
