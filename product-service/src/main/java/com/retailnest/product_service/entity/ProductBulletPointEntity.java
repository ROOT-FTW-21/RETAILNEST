package com.retailnest.product_service.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "RN_PRODUCT_BULLET_POINT")
public class ProductBulletPointEntity {

    @Id
    @Column(name = "PROD_PNT_ID", unique = true, nullable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY/* , generator = "PROD_PNT_ID_SEQ_GEN" */)
    //@SequenceGenerator(name = "PROD_PNT_ID_SEQ_GEN", sequenceName = "PROD_PNT_ID_SEQ_GEN", initialValue = 1, allocationSize = 1)
    private Long id;

    @Column(name = "PROD_PNT_TEXT", columnDefinition = "TEXT")
    private String pointText;

    @Column(name = "PROD_PNT_DISP_ORDER")
    private Integer displayOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "PROD_GRP_ID", nullable = false)
    private ProductBulletGroupEntity group;

    
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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

	public ProductBulletGroupEntity getGroup() {
		return group;
	}

	public void setGroup(ProductBulletGroupEntity group) {
		this.group = group;
	}
}
