package com.retailnest.product_service.service.applicationService;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.dto.ProductRequestDTO;
import com.retailnest.product_service.dto.ProductResponseDTO;

@Service
public interface IProductApplicationService {

	ProductResponseDTO createProduct(ProductRequestDTO dto, String userid, String roles);

	List<ProductImageResponseDTO> uploadAndPersistImages(List<MultipartFile> files, Long productId, String userid,
			String roles);

	ProductResponseDTO findProductByIdWithChildren(Long id, String userid, String roles);

	void deleteProduct(Long id, String userid, String roles);

	ProductResponseDTO updateProductDetails(Long id, ProductRequestDTO requestDTO, String userid, String roles);

}
