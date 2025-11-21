package com.retailnest.auth_service.controller;

import java.text.ParseException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailnest.auth_service.dto.AuthRequestDTO;
import com.retailnest.auth_service.dto.AuthResponseDTO;
import com.retailnest.auth_service.dto.JwtTokenDto;
import com.retailnest.auth_service.dto.UserLoginDto;
import com.retailnest.auth_service.entity.UserEntity;
import com.retailnest.auth_service.service.applicationService.IAuthApplicationService;
import com.retailnest.auth_service.service.applicationService.impl.UserDetailsApplicationServiceImpl;
import com.retailnest.auth_service.service.componentService.IAuthComponentService;
import com.retailnest.auth_service.service.utility.JwtUtil;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private IAuthApplicationService authApplicationService;

    @Autowired
    private IAuthComponentService authComponentService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserDetailsApplicationServiceImpl userDetailsApplicationService;

    @Autowired
    private JwtUtil jwtUtil;

    @PostMapping(value = "/create-user", consumes = "application/json")
    public ResponseEntity<AuthResponseDTO> createUser(@Valid @RequestBody AuthRequestDTO dto) throws ParseException {
    	return ResponseEntity.ok().body(authApplicationService.createUser(dto));
    }

    @GetMapping(value = "/get-user/{id}", produces = "application/json")
    public ResponseEntity<AuthResponseDTO> getUserById(@PathVariable Long id) {
        return ResponseEntity.ok(authComponentService.getUserById(id));
    }

    @GetMapping(value = "/login", produces = "application/json")
    public JwtTokenDto loginUser(@RequestBody UserLoginDto loginDto) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getIdentifier(), loginDto.getPassword()));
        } catch (RuntimeException e) {
            throw new RuntimeException(e);
        }
        
        final UserDetails userDetails = userDetailsApplicationService.loadUserByUsername(loginDto.getIdentifier());
        final String jwt = jwtUtil.generateToken(userDetails.getUsername());

        return new JwtTokenDto(jwt);
    }
    
    @GetMapping(value = "/getAllUsers", produces = "application/json")
    public ResponseEntity<List<AuthResponseDTO>> findAllUsers() {
    	return ResponseEntity.ok().body(authComponentService.getAllUsers());
    }
}
