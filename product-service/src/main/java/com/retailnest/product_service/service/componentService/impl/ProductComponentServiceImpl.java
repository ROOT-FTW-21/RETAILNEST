package com.retailnest.product_service.service.componentService.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailnest.product_service.dto.ProductResponseDTO;
import com.retailnest.product_service.dto.mapper.ProductMapper;
import com.retailnest.product_service.entity.ProductEntity;
import com.retailnest.product_service.exception.ProductNotFoundException;
import com.retailnest.product_service.repository.IProductRepository;
import com.retailnest.product_service.service.componentService.IProductComponentService;

@Service
public class ProductComponentServiceImpl implements IProductComponentService {
	
	private static final String HIGH_DATE = "31-12-9999 00:00:00";
	
	@Autowired
	private IProductRepository repository;

	@Override
	public ProductEntity createProduct(ProductEntity productEntity) {
		return repository.save(productEntity);
	}

	@Override
	public ProductEntity getProductById(Long productId) {
		return repository.findById(productId)
				.orElseThrow(() -> new ProductNotFoundException("Product with ID: " + productId + " not found"));
	}
	
	@Override
	public List<ProductResponseDTO> findActiveProductsWithChildren() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat);
		
		List<ProductEntity> entity = repository.findActiveProductsWithChildren(endDate);
		
		return entity.stream().filter(Objects::nonNull).map(Objects -> ProductMapper.toDto(Objects)).collect(Collectors.toList());
	}

	@Override
	public List<ProductResponseDTO> findActiveProductsWithImages() {
		DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat);
		
		List<ProductEntity> entity = repository.findActiveProductsWithImages(endDate);
		
		return entity.stream().filter(Objects::nonNull).map(Objects -> ProductMapper.toDto(Objects)).collect(Collectors.toList());
	}

	@Override
	public ProductEntity findProductByIdWithChildren(Long id) {
		return repository.findProductByIdWithChildren(id);
	}

	@Override
	public ProductEntity updateProductDetails(ProductEntity productEntity) {
		return repository.save(productEntity);
	}

}
