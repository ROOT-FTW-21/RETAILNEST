package com.retailnest.product_service.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.retailnest.product_service.entity.ProductEntity;

@Repository
public interface IProductRepository extends JpaRepository<ProductEntity, Long> {

	@Query("SELECT DISTINCT p FROM ProductEntity p " + "JOIN FETCH p.bulletGroups bg "
			+ "JOIN FETCH bg.bulletPoints bp " + "JOIN FETCH p.images img " + "WHERE p.deletedFlag = 'N' "
			+ "AND p.recordEndDate = :highDate")
	List<ProductEntity> findActiveProductsWithChildren(@Param("highDate") LocalDateTime highDate);

	@Query("SELECT DISTINCT p FROM ProductEntity p " + "JOIN FETCH p.images img " + "WHERE p.deletedFlag = 'N' "
			+ "AND p.recordEndDate = :highDate")
	List<ProductEntity> findActiveProductsWithImages(LocalDateTime endDate);

	@Query("SELECT DISTINCT p FROM ProductEntity p " + "JOIN FETCH p.bulletGroups bg "
			+ "JOIN FETCH bg.bulletPoints bp " + "JOIN FETCH p.images img " + "WHERE p.id = :id")
	ProductEntity findProductByIdWithChildren(@Param("id")Long id);

}
