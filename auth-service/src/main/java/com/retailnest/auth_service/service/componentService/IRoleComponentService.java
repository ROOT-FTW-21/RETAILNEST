package com.retailnest.auth_service.service.componentService;

import org.springframework.stereotype.Service;

import com.retailnest.auth_service.entity.UserRolesEntity;

@Service
public interface IRoleComponentService {

	UserRolesEntity saveRole(UserRolesEntity rolesEntity);

}
