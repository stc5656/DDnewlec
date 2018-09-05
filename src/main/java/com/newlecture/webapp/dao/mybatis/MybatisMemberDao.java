package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Repository;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.entity.Member;

/*@Component*/
@Repository //@component, @service, @entity
public class MybatisMemberDao implements MemberDao {
	
	@Autowired
	private SqlSession sqlSession;
	
	public int insert(Member member) {

		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
				
		return memberDao.insert(member);
	}

	public int update(Member member) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.update(member);
	}

	public int delete(String id) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.delete(id);
	}

	public Member get(String id) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.get(id);
	}

	public List<Member> getList() {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.getList("id", "", 1);
	}

	public List<Member> getList(int page) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.getList("id", "", page);
	}

	public List<Member> getList(String field, String query) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.getList(field, query, 1);
	}

	public List<Member> getList(String field, String query, int page) {
		MemberDao memberDao = sqlSession.getMapper(MemberDao.class);
		
		return memberDao.getList(field, query, page);
	}

}
