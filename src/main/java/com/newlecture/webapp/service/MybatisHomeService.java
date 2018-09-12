package com.newlecture.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.webapp.dao.MemberDao;
import com.newlecture.webapp.dao.MemberRoleDao;
import com.newlecture.webapp.entity.Member;
import com.newlecture.webapp.entity.MemberRole;

@Service
public class MybatisHomeService {
	
	// ioc�� ���� �Ŀ� �����ٰ� ���°�
	@Autowired
	private MemberRoleDao memberRoleDao;	
	
	@Autowired
	private MemberDao memberDao;	
	
	public String getDefaultRoleName(String memberId) {
		
		List<MemberRole> list = memberRoleDao.getList(memberId);
			
		String roleName = "ROLE_STUDENT";
		for(MemberRole role : list)
			if(role.getDefaultRole())
				roleName = role.getRoleName();
		
		return roleName;		
	}

	public boolean ischeckEmailDuplicated(String email) {
		
		// 
		
		Member member = memberDao.getByEmail(email);
		
		if(member == null)
			return true;
		
		// 기본값이라서 이렇게 한거
		return false;
	}

	public boolean isIdDuplicated(String id) {

		Member member = memberDao.get(id);
		
		if(member != null)
			return true;
		
		return false;
	}

	public int insertMember(Member member) {

		int result = memberDao.insert(member);
		memberRoleDao.insert(new MemberRole(member.getId(),"ROLE_STUDENT", true));
		
		return result;
	}	
}
