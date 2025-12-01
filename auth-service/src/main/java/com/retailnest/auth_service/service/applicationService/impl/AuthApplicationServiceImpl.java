package com.retailnest.auth_service.service.applicationService.impl;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.retailnest.auth_service.dto.AuthRequestDTO;
import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.dto.mapper.UserMapper;
import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.entity.UserRolesEntity;
import com.retailnest.auth_service.exception.EmailAlreadyExistsException;
import com.retailnest.auth_service.exception.MobileAlreadyExistsException;
import com.retailnest.auth_service.exception.UnauthorizedException;
import com.retailnest.auth_service.exception.UserIdAlreadyExistsException;
import com.retailnest.auth_service.service.applicationService.IAuthApplicationService;
import com.retailnest.auth_service.service.componentService.IAuthComponentService;
import com.retailnest.auth_service.service.componentService.IRoleComponentService;

@Service
public class AuthApplicationServiceImpl implements IAuthApplicationService {
	@Autowired
	private IAuthComponentService authComponentService;

	@Autowired
	private IRoleComponentService roleComponentService;

//	@Autowired
//	private UserDetailsApplicationServiceImpl userDetailsComponentService;

	BCryptPasswordEncoder encoder = new BCryptPasswordEncoder(12);

	private static final String HIGH_DATE = "31-12-9999 00:00:00";

	@Override
	public AuthResponseDTO createUser(AuthRequestDTO dto) throws ParseException {
		if (dto.getEmail() != null) {
			if (authComponentService.getUserByEmail(dto.getEmail()) != null) {
				throw new EmailAlreadyExistsException("Email already present: " + dto.getEmail());
			}
		}

		if (dto.getMobile() != null) {
			if (authComponentService.getUserByMobile(Long.parseLong(dto.getMobile())) != null) {
				throw new MobileAlreadyExistsException("Mobile already present: " + dto.getMobile());
			}
		}

		if (dto.getUserId() != null) {
			if (authComponentService.getUserByUserid(dto.getUserId()) != null) {
				throw new UserIdAlreadyExistsException("User ID already present: " + dto.getUserId());
			}
		}
		
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String now = LocalDateTime.now().format(dateFormat1);
		LocalDateTime startDate = LocalDateTime.parse(now, dateFormat1);
		LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat1);
		
		UserEntity userEntity = new UserEntity();
		userEntity = UserMapper.toModel(dto);
		userEntity.setRecordStartDate(startDate);
		userEntity.setRecordEndDate(endDate);
		userEntity.setDeletedFlag('N');
		userEntity.setCreatedBy("SYSTEM");

		UserRolesEntity rolesEntity = new UserRolesEntity();
		rolesEntity.setUserid(userEntity.getUserid());
		rolesEntity.setRole("USER");
		rolesEntity.setRecordStartDate(startDate);
		rolesEntity.setRecordEndDate(endDate);
		rolesEntity.setDeletedFlag('N');
		rolesEntity.setCreatedBy("SYSTEM");
		
		
		roleComponentService.saveRole(rolesEntity);
		UserEntity savedUser = authComponentService.saveUser(userEntity);

		return UserMapper.toDTO(savedUser);
	}

	@Override
	public List<AuthResponseDTO> getAllUsers(String currentUserId, String userRolesString) {
		List<String> roles = Arrays.asList(userRolesString.split(","));
		
		if (roles.contains("ADMIN")) {
			return authComponentService.getAllUsers();
		} else {
			throw new UnauthorizedException("User ID: " + currentUserId + " with roles " + userRolesString + " is not allowed for this request.");
		}
	}

	@Override
	public AuthResponseDTO getUserById(Long id, String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));
		
		if (roleList.contains("ADMIN") || roleList.contains("MAINTENANCE")) {
			return authComponentService.getUserById(id);
		} else {
			throw new UnauthorizedException("User ID: " + userid + " with roles " + roles + " is not allowed for this request.");
		}
	}
}
