package com.retailnest.auth_service.dto.mapper;

import java.time.LocalDate;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.retailnest.auth_service.dto.AuthRequestDTO;
import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.entity.UserRolesEntity;
import com.retailnest.auth_service.exception.InvalidMobileNumberException;

public class UserMapper {
	static BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);
	
    public static AuthResponseDTO toDTO(UserEntity user) {
        AuthResponseDTO responseDTO = new AuthResponseDTO();
        responseDTO.setId(user.getId().toString());
        responseDTO.setFirstName(user.getFirstName());
        responseDTO.setLastName(user.getLastName());
        responseDTO.setEmail(user.getEmail());
        responseDTO.setUserId(user.getUserid());
        responseDTO.setAddress(user.getAddress());
        responseDTO.setMobile(user.getMobile().toString());
        responseDTO.setDateOfBirth(user.getDob().toString());
        responseDTO.setCreatedBy(user.getCreatedBy());
        responseDTO.setDeletedFlag(user.getDeletedFlag().toString());
        responseDTO.setRecordEndDate(user.getRecordEndDate().toString());
        responseDTO.setRecordStartDate(user.getRecordStartDate().toString());
        if (user.getUserRoles() != null) {
        	responseDTO.setRoleSet(user.getUserRoles().stream().filter(Objects::nonNull).map(UserRolesEntity::getRole).collect(Collectors.toSet()));
		}
        
        return responseDTO;
    }

    public static UserEntity toModel(AuthRequestDTO authRequestDTO) {
    	UserEntity user = new UserEntity();
        user.setFirstName(authRequestDTO.getFirstName());
        user.setLastName(authRequestDTO.getLastName());
        user.setEmail(authRequestDTO.getEmail());
        user.setDob(LocalDate.parse(authRequestDTO.getDateOfBirth()));
        user.setUserid(authRequestDTO.getUserId());
        user.setPassword(encoder.encode(authRequestDTO.getPassword()));
        try {
        	user.setMobile(Long.parseLong(authRequestDTO.getMobile()));
		} catch (InvalidMobileNumberException e) {
			throw new InvalidMobileNumberException("Not a valid number");
		}
        user.setAddress(authRequestDTO.getAddress());

        return user;
    }
}
