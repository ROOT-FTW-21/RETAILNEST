package com.retailnest.product_service.dto;

public class ProductBulletPointRequestDTO {
	private String pointText;
	private Integer displayOrder;

	public String getPointText() {
		return pointText;
	}

	public void setPointText(String pointText) {
		this.pointText = pointText;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}
}
