package com.retailnest.order_service.entity.constants;

public enum EOrderStatus {
	PENDING("PAYMENT-PENDING"),
    ACCEPTED("ORDER-ACCEPTED"),
    SHIPPED("ORDER-SHIPPED"),
    DELIVERED("ORDER-DELIVERED"),
    CANCELLED("ORDER-CANCELLED"),
    RETURN_INITIATED("ORDER-RETURN-INITIATED"),
    RETURN_ACCEPTED("ORDER-RETURN-ACCEPTED"),
    RETURNED("ORDER-RETURNED"),
    REFUNDED("ORDER-REFUNDED");
	
	private final String label;
	
	EOrderStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}
}
