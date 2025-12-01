package com.retailnest.product_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailnest.product_service.entity.ProductImageEntity;

@Repository
public interface IProductImageRepository extends JpaRepository<ProductImageEntity, Long> {

}
