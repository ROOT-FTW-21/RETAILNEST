package com.retailnest.product_service.dto;

import java.util.List;

public class ProductResponseDTO {
	private String id;
	private String name;
	private String brand;
	private String category;
	private String price;
	private String about;
	private String sku;
	private String recordStartDate;
	private String recordEndDate;
	private String deletedFlag;
	private String createdBy;

	private List<ProductBulletGroupResponseDTO> bulletGroups;
	private List<ProductImageResponseDTO> images;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getAbout() {
		return about;
	}

	public void setAbout(String about) {
		this.about = about;
	}

	public String getSku() {
		return sku;
	}

	public void setSku(String sku) {
		this.sku = sku;
	}

	public String getRecordStartDate() {
		return recordStartDate;
	}

	public void setRecordStartDate(String recordStartDate) {
		this.recordStartDate = recordStartDate;
	}

	public String getRecordEndDate() {
		return recordEndDate;
	}

	public void setRecordEndDate(String recordEndDate) {
		this.recordEndDate = recordEndDate;
	}

	public String getDeletedFlag() {
		return deletedFlag;
	}

	public void setDeletedFlag(String deletedFlag) {
		this.deletedFlag = deletedFlag;
	}

	public String getCreatedBy() {
		return createdBy;
	}

	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

	public List<ProductBulletGroupResponseDTO> getBulletGroups() {
		return bulletGroups;
	}

	public void setBulletGroups(List<ProductBulletGroupResponseDTO> bulletGroups) {
		this.bulletGroups = bulletGroups;
	}

	public List<ProductImageResponseDTO> getImages() {
		return images;
	}

	public void setImages(List<ProductImageResponseDTO> images) {
		this.images = images;
	}
}
