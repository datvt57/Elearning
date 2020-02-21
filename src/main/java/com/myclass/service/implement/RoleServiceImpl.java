package com.myclass.service.implement;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.myclass.entity.Role;
import com.myclass.repository.RoleRepository;
import com.myclass.service.RoleService;

@Service
public class RoleServiceImpl implements RoleService {

	@Autowired
	private RoleRepository roleDAO;

	@Override
	public List<Role> findAll() {
		return roleDAO.findAll();
	}

	@Override
	public Role findById(String id) {
		if (roleDAO.existsById(id)) {
			return roleDAO.findById(id).get();
		} else {
			return null;
		}
	}

	@Override
	public boolean insertRole(Role role) {
		try {
			if (role != null) {
				role.setId(UUID.randomUUID().toString().replace("-", ""));
				roleDAO.save(role);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateRole(String id, Role role) {
		Role entity = findById(id);
		try {
			if (entity != null) {
				role.setId(entity.getId());
				roleDAO.save(role);
				return true;
			} else {
				return false;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean deleteRole(String id) {
		Role entity = findById(id);
		if (entity != null) {
			roleDAO.delete(entity);
			return true;
		} else {
			return false;
		}
	}

}
