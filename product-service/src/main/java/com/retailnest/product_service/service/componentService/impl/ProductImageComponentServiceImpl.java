package com.retailnest.product_service.service.componentService.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.dto.mapper.ProductMapper;
import com.retailnest.product_service.entity.ProductImageEntity;
import com.retailnest.product_service.repository.IProductImageRepository;
import com.retailnest.product_service.service.componentService.IProductImageComponentService;

@Service
public class ProductImageComponentServiceImpl implements IProductImageComponentService{

	@Autowired
	private IProductImageRepository imageRepository;
	
	@Override
	public ProductImageResponseDTO save(ProductImageEntity imageEntity) {
		return ProductMapper.toDto(imageRepository.save(imageEntity));
	}

}
