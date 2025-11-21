package com.retailnest.auth_service.service.applicationService.impl;

import java.util.ArrayList;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.service.componentService.IAuthComponentService;

@Service
public class UserDetailsApplicationServiceImpl implements UserDetailsService {
	@Autowired
	private IAuthComponentService authComponentService;

	@Override
	public UserDetails loadUserByUsername(String identifier) throws UsernameNotFoundException {
		UserEntity appUser;

		if (isIdentifierNumber(identifier)) {
			appUser = authComponentService.getUserByMobile(Long.parseLong(identifier));

		} else {
			appUser = authComponentService.getUserByEmail(identifier);
		}

		if (appUser == null) {
			throw new UsernameNotFoundException("User not found: " + identifier);
		}

		Set<GrantedAuthority> authorities = appUser.getUserRoles().stream()
				.map(roleEntity -> new SimpleGrantedAuthority(roleEntity.getRole())).collect(Collectors.toSet());

		if (isIdentifierNumber(identifier)) {
			return new User(String.valueOf(appUser.getMobile()), appUser.getPassword(), authorities);
		} else {
			return new User(appUser.getEmail(), appUser.getPassword(), authorities);
		}
	}

	public static boolean isIdentifierNumber(String identifier) {
		if (identifier == null)
			return false;

		try {
			@SuppressWarnings("unused")
			Long number = Long.parseLong(identifier);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}
}
