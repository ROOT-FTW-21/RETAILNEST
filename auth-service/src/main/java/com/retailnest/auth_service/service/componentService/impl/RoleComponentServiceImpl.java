package com.retailnest.auth_service.service.componentService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailnest.auth_service.entity.UserRolesEntity;
import com.retailnest.auth_service.repository.IRoleRepository;
import com.retailnest.auth_service.service.componentService.IRoleComponentService;

@Service
public class RoleComponentServiceImpl implements IRoleComponentService{
	@Autowired
	private IRoleRepository repository;
	
	@Override
	public UserRolesEntity saveRole(UserRolesEntity rolesEntity) {
		return repository.save(rolesEntity);	
	}

}
