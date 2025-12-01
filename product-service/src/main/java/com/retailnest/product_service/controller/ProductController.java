package com.retailnest.product_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.dto.ProductRequestDTO;
import com.retailnest.product_service.dto.ProductResponseDTO;
import com.retailnest.product_service.service.applicationService.IProductApplicationService;
import com.retailnest.product_service.service.componentService.IProductComponentService;

@RestController
@RequestMapping("/product")
public class ProductController {
	@Autowired
	private IProductApplicationService applicationService;

	@Autowired
	private IProductComponentService componentService;

	@PostMapping(value = "/create-product", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponseDTO> createProduct(@RequestBody ProductRequestDTO productRequestDTO,
			@RequestHeader("X-UserId") String userid, @RequestHeader("X-Roles") String roles) {
		ProductResponseDTO responseDTO = applicationService.createProduct(productRequestDTO, userid, roles);

		return ResponseEntity.status(HttpStatus.CREATED).body(responseDTO);
	}

	@PostMapping(value = "/upload-image/{productId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	public ResponseEntity<List<ProductImageResponseDTO>> uploadImage(@RequestPart("files") List<MultipartFile> files,
			@PathVariable Long productId, @RequestHeader("X-UserId") String userid,
			@RequestHeader("X-Roles") String roles) {
		List<ProductImageResponseDTO> productImages = applicationService.uploadAndPersistImages(files, productId,
				userid, roles);

		return ResponseEntity.status(HttpStatus.CREATED).body(productImages);
	}

	@GetMapping("/get-product/{id}")
	public ResponseEntity<ProductResponseDTO> findProductByIdWithChildren(@PathVariable Long id,
			@RequestHeader("X-UserID") String userid, @RequestHeader("X-Roles") String roles) {
		return ResponseEntity.status(HttpStatus.OK)
				.body(applicationService.findProductByIdWithChildren(id, userid, roles));
	}

	@DeleteMapping("/delete-product/{id}")
	public ResponseEntity<String> deleteProduct(@PathVariable Long id, @RequestHeader("X-UserID") String userid,
			@RequestHeader("X-Roles") String roles) {
		applicationService.deleteProduct(id, userid, roles);
		return ResponseEntity.status(HttpStatus.OK).body("Product Deleted");
	}

	@GetMapping("/get-all-products-and-children")
	public ResponseEntity<List<ProductResponseDTO>> findActiveProductsWithChildren() {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.findActiveProductsWithChildren());
	}

	@GetMapping("/get-all-products-with-images")
	public ResponseEntity<List<ProductResponseDTO>> findActiveProductsWithImages() {
		return ResponseEntity.status(HttpStatus.OK).body(componentService.findActiveProductsWithImages());
	}

	@PostMapping(value = "/update-product/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductResponseDTO> updateProduct(@PathVariable Long id,
			@RequestHeader("X-Roles") String roles, @RequestHeader("X-UserID") String userid,
			@RequestBody ProductRequestDTO requestDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(applicationService.updateProductDetails(id, requestDTO, userid, roles));
	}
}
