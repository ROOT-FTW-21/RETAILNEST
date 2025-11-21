package com.retailnest.auth_service.dto;

import java.util.Set;

public class AuthResponseDTO {
	private String id, firstName, lastName, userId, mobile, email, address, dateOfBirth, createdBy, deletedFlag,
			recordEndDate, recordStartDate;
	
	private Set<String> roleSet;
	
	
	public Set<String> getRoleSet() {
		return roleSet;
	}

	public void setRoleSet(Set<String> roleSet) {
		this.roleSet = roleSet;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(String recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	public String getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(String recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
}
