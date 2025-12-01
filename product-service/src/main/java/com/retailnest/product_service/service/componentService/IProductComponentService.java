package com.retailnest.product_service.service.componentService;

import java.util.List;

import org.springframework.stereotype.Service;

import com.retailnest.product_service.dto.ProductResponseDTO;
import com.retailnest.product_service.entity.ProductEntity;

@Service
public interface IProductComponentService {

	ProductEntity createProduct(ProductEntity productEntity);

	ProductEntity getProductById(Long productId);

	List<ProductResponseDTO> findActiveProductsWithChildren();

	List<ProductResponseDTO> findActiveProductsWithImages();

	ProductEntity findProductByIdWithChildren(Long id);

	ProductEntity updateProductDetails(ProductEntity productEntity);

}
