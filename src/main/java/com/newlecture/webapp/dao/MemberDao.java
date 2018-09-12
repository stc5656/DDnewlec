package com.newlecture.webapp.dao;

import java.util.List;

import com.newlecture.webapp.entity.Member;

public interface MemberDao {
		
	int update(Member member);
	int delete(String id);
	
	Member get(String id);
	List<Member> getList();
	List<Member> getList(int page);
	List<Member> getList(String field, String query);
	List<Member> getList(String field, String query, int page);
	Member getByEmail(String email);
	int insert(Member member);
	
	
}
