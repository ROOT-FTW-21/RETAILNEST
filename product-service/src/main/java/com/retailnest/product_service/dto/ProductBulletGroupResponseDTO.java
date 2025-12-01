package com.retailnest.product_service.dto;

import java.util.List;

public class ProductBulletGroupResponseDTO {
	private String id;
	private String heading;
	private Integer displayOrder;
	private List<ProductBulletPointResponseDTO> bulletPoints;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public List<ProductBulletPointResponseDTO> getBulletPoints() {
		return bulletPoints;
	}

	public void setBulletPoints(List<ProductBulletPointResponseDTO> bulletPoints) {
		this.bulletPoints = bulletPoints;
	}
}
