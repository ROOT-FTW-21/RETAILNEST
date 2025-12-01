package com.retailnest.auth_service.service.componentService.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.dto.mapper.UserMapper;
import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.exception.RuntimeExceptions;
import com.retailnest.auth_service.repository.IUserRepository;
import com.retailnest.auth_service.service.componentService.IAuthComponentService;

@Service
public class AuthComponentServiceImpl implements IAuthComponentService {
	@Autowired
	private IUserRepository userRepository;

	private static final String HIGH_DATE = "31-12-9999 00:00:00";

	@Override
	public UserEntity saveUser(UserEntity userEntity) {

		return userRepository.save(userEntity);
	}

	@Override
	public List<AuthResponseDTO> getAllUsers() {
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat1);

		return userRepository.findByRecordEndDateAndDeletedFlag(endDate, 'N').stream().filter(Objects::nonNull)
				.map(e -> UserMapper.toDTO(e)).collect(Collectors.toList());
	}

	@Override
	public AuthResponseDTO getUserById(Long userId) {

		return UserMapper.toDTO(userRepository.findById(userId)
				.orElseThrow(() -> new UsernameNotFoundException("UserEntity with ID " + userId + " not found")));
	}

	/*
	 * @Override public UserEntity getUserByUserLegacyNumber(String
	 * userLegacyNumber) {
	 * iUserRepository.findUserByUserLegacyNumber(userLegacyNumber); }
	 */

	@Override
	public void deleteUser(UserEntity userEntity) {
		userRepository.delete(userEntity);
	}

	@Override
	public UserEntity updateUser(UserEntity userEntity) {
		return userRepository.save(userEntity);
	}

	@Override
	public UserEntity getUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public UserEntity getUserByMobile(Long number) {
		return userRepository.findByMobile(number);
	}

	@Override
	public UserEntity getUserByUserid(String userId) {
		// TODO Auto-generated method stub
		return userRepository.findByUserid(userId);
	}
}
