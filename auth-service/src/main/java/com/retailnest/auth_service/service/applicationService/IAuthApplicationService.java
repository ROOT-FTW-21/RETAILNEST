package com.retailnest.auth_service.service.applicationService;

import java.text.ParseException;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retailnest.auth_service.dto.AuthRequestDTO;
import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.entity.UserEntity;

@Service
public interface IAuthApplicationService {
	AuthResponseDTO createUser(AuthRequestDTO dto) throws ParseException;
}
