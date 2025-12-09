package com.retailnest.order_service.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "RN_ORDER_MST")
public class OrderEntity {
	@Id
	@Column(name = "ORD_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "USR_USERID", unique = true, nullable = false)
	private String userid;

	@Column(name = "PROD_ID", unique = true, nullable = false)
	private Long productId;

	@Column(name = "ORD_QUANTITY", unique = false, nullable = false)
	private Integer orderQuantity;

	@Column(name = "ORD_DELETE_FLAG", unique = false, nullable = false)
	private Character deletedFlag;

	@Column(name = "ORD_STATUS", unique = false, nullable = false)
	private String orderStatus;

	@Column(name = "ORD_DATE", unique = false, nullable = false)
	private LocalDateTime orderDateTime;

	@Column(name = "ORD_PAYMENT_STATUS", unique = false, nullable = false)
	private String paymentStatus;

	@Column(name = "ORD_AMOUNT", unique = false, nullable = false)
	private BigDecimal amount;

	@Column(name = "PROD_IMG_URL", unique = true, nullable = false)
	private String imageURL;
	
	@Column(name = "ORD_PAYMENT_TYPE", unique = false, nullable = false)
	private String paymentType;

	public String getPaymentType() {
		return paymentType;
	}

	public void setPaymentType(String paymentType) {
		this.paymentType = paymentType;
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

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public Integer getOrderQuantity() {
		return orderQuantity;
	}

	public void setOrderQuantity(Integer orderQuantity) {
		this.orderQuantity = orderQuantity;
	}

	public Character getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(Character deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(String orderStatus) {
		this.orderStatus = orderStatus;
	}

	public LocalDateTime getOrderDateTime() {
		return orderDateTime;
	}

	public void setOrderDateTime(LocalDateTime orderDateTime) {
		this.orderDateTime = orderDateTime;
	}

	public String getPaymentStatus() {
		return paymentStatus;
	}

	public void setPaymentStatus(String paymentStatus) {
		this.paymentStatus = paymentStatus;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public void setAmount(BigDecimal amount) {
		this.amount = amount;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}
}
