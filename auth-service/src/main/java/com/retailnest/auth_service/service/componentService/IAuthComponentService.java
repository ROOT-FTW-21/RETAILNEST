package com.retailnest.auth_service.service.componentService;

import org.springframework.stereotype.Service;

import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.entity.UserEntity;

import java.util.List;

@Service
public interface IAuthComponentService {
    UserEntity getUserByEmail(String email);

    UserEntity saveUser(UserEntity userEntity);

    //UserEntity getUserByUsername(String identifier);

    List<AuthResponseDTO> getAllUsers();

    AuthResponseDTO getUserById(Long userId);

    /* UserEntity getUserByUserLegacyNumber(String userLegacyNumber); */

    void deleteUser(UserEntity userEntity);

    UserEntity updateUser(UserEntity userEntity);

    UserEntity getUserByMobile(Long number);

	Object getUserByUserid(String userId);
}
