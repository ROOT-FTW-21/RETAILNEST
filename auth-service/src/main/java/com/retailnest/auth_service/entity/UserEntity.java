package com.retailnest.auth_service.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;

@Entity
@Table(name = "RN_USER_MST")
public class UserEntity {
    @Id
    @Column(name = "USR_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_ID_SEQ_GEN")
    @SequenceGenerator(name = "USER_ID_SEQ_GEN", sequenceName = "USER_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
    private Long id;

    /*
     * @Column(name = "USR_LEG_NUM", unique = true, nullable = false)
     *
     * @GeneratedValue(generator = "USER_ID_SEQ_GEN")
     *
     * @GenericGenerator(name = "USER_ID_SEQ_GEN", strategy =
     * "com.project_w.UserService.service.sequence.UserSequenceGeneratorService")
     * private String userLegacyNumber;
     */

    @Column(name = "USR_FIRST_NAME", nullable = false)
    private String firstName;
    
    @Column(name = "USR_LAST_NAME", nullable = false)
    private String lastName;

    @Column(name = "USR_EMAIL", nullable = false, unique = true)
    @Email
    private String email;
    
    @Column(name = "USR_DOB", nullable = false, unique = false)
    private LocalDate dob;

    @Column(name = "USR_USERID", nullable = true, unique = true)
    private String userid;

    @Column(name = "USR_PASSWORD", nullable = false, unique = false)
    private String password;

    @Column(name = "USR_RECORD_START_DATE", nullable = false)
    private LocalDateTime recordStartDate;

    @Column(name = "USR_RECORD_END_DATE", nullable = false)
    private LocalDateTime recordEndDate;

    @Column(name = "USR_DELETED_FLAG", nullable = false)
    private Character deletedFlag;

    @Column(name = "USR_CREATED_BY", nullable = false)
    private String createdBy;
    
    @Column(name = "USR_MOBILE_NO", nullable = false, unique = true)
    private Long mobile;
    
    @Column(name = "USR_ADDRESS", nullable = false)
    private String address;
    
    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "USR_USERID", referencedColumnName = "USR_USERID")
    private Set<UserRolesEntity> userRoles;
        

    public Set<UserRolesEntity> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRolesEntity> userRoles) {
		this.userRoles = userRoles;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /*
     * public String getUserLegacyNumber() { return userLegacyNumber; }
     *
     * public void setUserLegacyNumber(String userLegacyNumber) {
     * this.userLegacyNumber = userLegacyNumber; }
     */

    public String getEmail() {
        return email;
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

	public void setEmail(String email) {
        this.email = email;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userName) {
        this.userid = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

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

	public Long getMobile() {
		return mobile;
	}

	public void setMobile(Long mobile) {
		this.mobile = mobile;
	}
}
