package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.newlecture.webapp.dao.LevelDao;
import com.newlecture.webapp.entity.Level;

@Repository
public class MybatisLevelDao implements LevelDao{

	@Autowired
	private SqlSession SqlSession;

	@Override
	public int insert(Level level) {

		LevelDao levelDao = SqlSession.getMapper(LevelDao.class);
				
		return levelDao.insert(level);
	}

	@Override
	public int delete(long id) {

		LevelDao levelDao = SqlSession.getMapper(LevelDao.class);
		
		return levelDao.delete(id);
	}

	@Override
	public int update(Level level) {
		
		LevelDao levelDao = SqlSession.getMapper(LevelDao.class);
		
		return levelDao.update(level);
	}

	@Override
	public Level get(long id) {
		
		LevelDao levelDao = SqlSession.getMapper(LevelDao.class);
		
		return levelDao.get(id);
	}

	@Override
	public List<Level> getList() {
		
		LevelDao levelDao = SqlSession.getMapper(LevelDao.class);
				
		return levelDao.getList();
	}
	
}