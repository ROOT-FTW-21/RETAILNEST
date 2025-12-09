package com.retailnest.order_service.entity.constants;

public enum EPaymentStatus {
	PENDING("PENDING-PAYMENT"),
	CASH("CASH-ON-DELIVERY"),
	PROCESSING("PROCESSING-PAYMENT"),
	FAILED("PAYMENT-FAILED"),
	PAID("PAID");
	
	private final String label;
	
	private EPaymentStatus(String label) {
		this.label = label;
	}

	public String getLabel() {
		return label;
	}	
}
