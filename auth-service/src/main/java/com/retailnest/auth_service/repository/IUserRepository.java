package com.retailnest.auth_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.entity.UserEntity;

@Repository
public interface IUserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);

    UserEntity findByMobile(Long number);

	UserEntity findByUserid(String userId);

	List<AuthResponseDTO> findByRecordEndDateAndDeletedFlag(LocalDateTime endDate, char c);

    //AppUser findByUsername(String identifier);
}
