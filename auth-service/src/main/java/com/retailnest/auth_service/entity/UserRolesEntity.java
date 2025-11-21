package com.retailnest.auth_service.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "RN_USER_ROLE_MAP")
public class UserRolesEntity {
	@Id
	@Column(name = "ROL_MAP_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ROLE_ID_SEQ_GEN")
	@SequenceGenerator(name = "USER_ROLE_ID_SEQ_GEN", sequenceName = "USER_ROLE_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "USR_USERID", nullable = false, unique = false)
	private String userid;

	@Column(name = "ROL_MAP_ROLE", unique = false, nullable = false)
	private String role;

	@Column(name = "ROL_MAP_RECORD_START_DATE", nullable = false)
	private LocalDateTime recordStartDate;

	@Column(name = "ROL_MAP_RECORD_END_DATE", nullable = false)
	private LocalDateTime recordEndDate;

	@Column(name = "ROL_MAP_DELETED_FLAG", nullable = false)
	private Character deletedFlag;

	@Column(name = "ROL_MAP_CREATED_BY", nullable = false)
	private String createdBy;

	
	public LocalDateTime getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(LocalDateTime recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	public LocalDateTime getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(LocalDateTime recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	public Character getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Character deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}
}
