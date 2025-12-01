package com.retailnest.product_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RN_PRODUCT_IMAGE")
public class ProductImageEntity {

	@Id
	@Column(name = "PROD_IMG_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY/* , generator = "PROD_IMG_ID_SEQ_GEN" */)
	//@SequenceGenerator(name = "PROD_IMG_ID_SEQ_GEN", sequenceName = "PROD_IMG_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
	private Long id;

	@Column(name = "PROD_IMG_URL", nullable = false, columnDefinition = "TEXT")
	private String url;

	@Column(name = "PROD_IMP_DISPLAY_ORDER")
	private Integer displayOrder;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "PROD_ID", nullable = false)
	private ProductEntity product;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDisplayOrder() {
		return displayOrder;
	}

	public void setDisplayOrder(Integer displayOrder) {
		this.displayOrder = displayOrder;
	}

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

}
