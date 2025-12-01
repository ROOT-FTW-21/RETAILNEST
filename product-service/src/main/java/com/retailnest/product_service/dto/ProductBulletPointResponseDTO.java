package com.retailnest.product_service.dto;

public class ProductBulletPointResponseDTO {
	private String id;
	private String pointText;
	private Integer displayOrder;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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
