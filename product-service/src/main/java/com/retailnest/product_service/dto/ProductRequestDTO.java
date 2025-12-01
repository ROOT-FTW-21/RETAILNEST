package com.retailnest.product_service.dto;

import java.util.List;

public class ProductRequestDTO {
	private String name;
	private String brand;
	private String category;
	private String price;
	private String about;
	private String sku;

	private List<ProductBulletGroupRequestDTO> bulletGroups;
	private List<ProductImageRequestDTO> images;

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

	public List<ProductBulletGroupRequestDTO> getBulletGroups() {
		return bulletGroups;
	}

	public void setBulletGroups(List<ProductBulletGroupRequestDTO> bulletGroups) {
		this.bulletGroups = bulletGroups;
	}

	public List<ProductImageRequestDTO> getImages() {
		return images;
	}

	public void setImages(List<ProductImageRequestDTO> images) {
		this.images = images;
	}

}
