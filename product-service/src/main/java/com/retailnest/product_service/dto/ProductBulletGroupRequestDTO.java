package com.retailnest.product_service.dto;

import java.util.List;

public class ProductBulletGroupRequestDTO {
	private String heading;
	private Integer displayOrder;
	private List<ProductBulletPointRequestDTO> bulletPoints;

	public String getHeading() {
		return heading;
	}

	public void setHeading(String heading) {
		this.heading = heading;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public List<ProductBulletPointRequestDTO> getBulletPoints() {
		return bulletPoints;
	}

	public void setBulletPoints(List<ProductBulletPointRequestDTO> bulletPoints) {
		this.bulletPoints = bulletPoints;
	}
}
