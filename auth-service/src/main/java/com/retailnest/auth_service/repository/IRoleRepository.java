package com.retailnest.auth_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.retailnest.auth_service.entity.UserRolesEntity;

public interface IRoleRepository extends JpaRepository<UserRolesEntity, Long> {

}
