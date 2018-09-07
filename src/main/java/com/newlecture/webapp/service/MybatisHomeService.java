package com.newlecture.webapp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.newlecture.webapp.dao.MemberRoleDao;
import com.newlecture.webapp.entity.MemberRole;

@Service
public class MybatisHomeService {
	
	// ioc�� ���� �Ŀ� �����ٰ� ���°�
	@Autowired
	private MemberRoleDao memberRoleDao;	
	
	public String getDefaultRoleName(String memberId) {
		
		List<MemberRole> list = memberRoleDao.getList(memberId);
			
		String roleName = "ROLE_STUDENT";
		for(MemberRole role : list)
			if(role.getDefaultRole())
				roleName = role.getRoleName();		
		
		
		return roleName;		
	}	
}
