package com.newlecture.webapp.dao;

import java.util.List;

import com.newlecture.webapp.entity.Level;


public interface LevelDao {
	

		int insert(Level level);
		int delete(long id);
		int update(Level level);
		
		Level get(long id);
		List<Level> getList();	

	}

