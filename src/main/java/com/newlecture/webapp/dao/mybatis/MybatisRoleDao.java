package com.newlecture.webapp.dao.mybatis;

import java.util.List;

import org.apache.catalina.Role;
import org.apache.catalina.UserDatabase;

import com.newlecture.webapp.dao.RoleDao;

public class MybatisRoleDao implements Role, RoleDao {

	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	public int insert(com.newlecture.webapp.entity.Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int update(com.newlecture.webapp.entity.Role role) {
		// TODO Auto-generated method stub
		return 0;
	}

	public int delete(String id) {
		// TODO Auto-generated method stub
		return 0;
	}

	public com.newlecture.webapp.entity.Role get(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<com.newlecture.webapp.entity.Role> getList() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getDescription() {
		// TODO Auto-generated method stub
		return null;
	}

	public String getRolename() {
		// TODO Auto-generated method stub
		return null;
	}

	public UserDatabase getUserDatabase() {
		// TODO Auto-generated method stub
		return null;
	}

	public void setDescription(String arg0) {
		// TODO Auto-generated method stub

	}

	public void setRolename(String arg0) {
		// TODO Auto-generated method stub

	}

}
