package com.retailnest.auth_service.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class AuthRequestDTO {
	@NotBlank(message = "Fist Name is required")
    @Size(max = 40, message = "Fist Name cannot exceed 40 characters")
    private String firstName;
    
    @NotBlank(message = "Last Name is required")
    @Size(max = 40, message = "Last Name cannot exceed 40 characters")
    private String lastName;
    
    @NotBlank(message = "User ID is required")
    @Size(max = 100, message = "User ID cannot exceed 100 characters")
    private String userId;

    @NotBlank(message = "Mobile Number is required")
    @Size(max = 10, message = "Mobile Number cannot exceed 10 numbers")
    private String mobile;
    
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @NotBlank(message = "Address is required")
    private String address;

    @NotBlank(message = "Date of Birth is required")
    private String dateOfBirth;

    @NotBlank(message = "Password is required")
    @Size(max = 1000, message = "Password cannot exceed 100 characters")
    private String password;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getDateOfBirth() {
		return dateOfBirth;
	}

	public void setDateOfBirth(String dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
