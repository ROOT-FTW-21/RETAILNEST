package com.retailnest.product_service.service.componentService;

import org.springframework.stereotype.Service;

import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.entity.ProductImageEntity;

@Service
public interface IProductImageComponentService {

	ProductImageResponseDTO save(ProductImageEntity imageEntity);

}
