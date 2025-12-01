package com.retailnest.product_service.entity;

import jakarta.persistence.*;
import java.util.Set;

@Entity
@Table(name = "RN_PRODUCT_BULLET_GROUP")
public class ProductBulletGroupEntity {

    @Id
    @Column(name = "PROD_GRP_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY/* , generator = "PROD_GRP_ID_SEQ_GEN" */)
    //@SequenceGenerator(name = "PROD_GRP_ID_SEQ_GEN", sequenceName = "PROD_GRP_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PROD_GRP_HEADING", nullable = false)
    private String heading;

    @Column(name = "PROD_GRP_DISP_ORDER")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROD_ID", nullable = false)
    private ProductEntity product;

    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.EAGER)
    private Set<ProductBulletPointEntity> bulletPoints;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public ProductEntity getProduct() {
		return product;
	}

	public void setProduct(ProductEntity product) {
		this.product = product;
	}

	public Set<ProductBulletPointEntity> getBulletPoints() {
		return bulletPoints;
	}

	public void setBulletPoints(Set<ProductBulletPointEntity> bulletPoints) {
		this.bulletPoints = bulletPoints;
	}
}