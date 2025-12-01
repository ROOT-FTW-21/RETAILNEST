package com.retailnest.auth_service.service.applicationService;

import java.text.ParseException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.retailnest.auth_service.dto.AuthRequestDTO;
import com.retailnest.auth_service.dto.AuthResponseDTO;

@Service
public interface IAuthApplicationService {
	AuthResponseDTO createUser(AuthRequestDTO dto) throws ParseException;

	List<AuthResponseDTO> getAllUsers(String currentUserId, String userRolesString);

	AuthResponseDTO getUserById(Long id, String userid, String roles);
}
