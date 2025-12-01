package com.retailnest.product_service.entity;

import java.time.LocalDateTime;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "RN_PRODUCT_MST")
public class ProductEntity {
	@Id
    @Column(name = "PROD_ID", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY/*, generator = "PROD_ID_SEQ_GEN"*/)
//    @SequenceGenerator(name = "PROD_ID_SEQ_GEN", sequenceName = "PROD_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PROD_NAME", nullable = false)
    private String name;

    @Column(name = "PROD_BRAND", nullable = false)
    private String brand;

    @Column(name = "PROD_CATEGORY", nullable = false)
    private String category;

    @Column(name = "PROD_PRICE", nullable = false)
    private Double price;

    @Column(name = "PROD_ABOUT", columnDefinition = "TEXT")
    private String about;
    
    @Column(name = "PROD_SKU", nullable = false, unique = true)
    private String sku;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductBulletGroupEntity> bulletGroups;

    @Column(name = "PROD_START_DATE", nullable = false)
    private LocalDateTime recordStartDate;

    @Column(name = "PROD_RECORD_END_DATE", nullable = false)
    private LocalDateTime recordEndDate;

    @Column(name = "PROD_DELETED_FLAG", nullable = false)
    private Character deletedFlag;

    @Column(name = "PROD_CREATED_BY", nullable = false)
    private String createdBy;
    
    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private Set<ProductImageEntity> images;

        

	public Set<ProductImageEntity> getImages() {
		return images;
	}

	public void setImages(Set<ProductImageEntity> images) {
		this.images = images;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
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

	public Set<ProductBulletGroupEntity> getBulletGroups() {
		return bulletGroups;
	}

	public void setBulletGroups(Set<ProductBulletGroupEntity> bulletGroups) {
		this.bulletGroups = bulletGroups;
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
}
