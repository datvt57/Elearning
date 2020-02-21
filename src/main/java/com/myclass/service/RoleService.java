package com.myclass.service;

import java.util.List;

import com.myclass.entity.Role;

public interface RoleService {
	public List<Role> findAll();

	public Role findById(String id);

	public boolean insertRole(Role role);

	public boolean updateRole(String id, Role role);

	public boolean deleteRole(String id);
}
