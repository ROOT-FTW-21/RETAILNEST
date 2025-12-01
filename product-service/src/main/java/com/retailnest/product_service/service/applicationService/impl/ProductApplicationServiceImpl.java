package com.retailnest.product_service.service.applicationService.impl;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.dto.ProductRequestDTO;
import com.retailnest.product_service.dto.ProductResponseDTO;
import com.retailnest.product_service.dto.mapper.ProductMapper;
import com.retailnest.product_service.entity.ProductBulletGroupEntity;
import com.retailnest.product_service.entity.ProductBulletPointEntity;
import com.retailnest.product_service.entity.ProductEntity;
import com.retailnest.product_service.entity.ProductImageEntity;
import com.retailnest.product_service.exception.AccessUnauthorizedException;
import com.retailnest.product_service.service.applicationService.IProductApplicationService;
import com.retailnest.product_service.service.componentService.IProductComponentService;
import com.retailnest.product_service.service.componentService.IProductImageComponentService;

@Service
public class ProductApplicationServiceImpl implements IProductApplicationService {
	private static final String UPLOAD_DIR_BASE = "retailnest/product_images/";
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_MAINTAINANCE = "MAINTAINANCE";
	private static final String ROLE_USER = "USER";

	@Autowired
	private IProductComponentService componentService;

	@Autowired
	private IProductImageComponentService imageComponentService;

	@Autowired
	private Cloudinary cloudinary;

	private static final String HIGH_DATE = "31-12-9999 00:00:00";

	@Override
	public ProductResponseDTO createProduct(ProductRequestDTO dto, String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String now = LocalDateTime.now().format(dateFormat1);
			LocalDateTime startDate = LocalDateTime.parse(now, dateFormat1);
			LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat1);

			ProductEntity productEntity = ProductMapper.toModel(dto);
			productEntity.setCreatedBy(userid);
			productEntity.setDeletedFlag('N');
			productEntity.setRecordStartDate(startDate);
			productEntity.setRecordEndDate(endDate);
			productEntity.setImages(null);
			linkNestedEntities(productEntity);

			ProductEntity savedProduct = componentService.createProduct(productEntity);

			return ProductMapper.toDto(savedProduct);
		} else {
			throw new AccessUnauthorizedException("User: " + userid + " whith roles: " + roles + " is not authorized.");
		}

	}

	private void linkNestedEntities(ProductEntity product) {
		Set<ProductBulletGroupEntity> groups = product.getBulletGroups() != null ? product.getBulletGroups()
				: Collections.emptySet();

		for (ProductBulletGroupEntity group : groups) {
			group.setProduct(product);
			Set<ProductBulletPointEntity> points = group.getBulletPoints() != null ? group.getBulletPoints()
					: Collections.emptySet();
			points.forEach(point -> point.setGroup(group));
		}
	}

	@Override
	public List<ProductImageResponseDTO> uploadAndPersistImages(List<MultipartFile> files, Long productId,
			String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			List<ProductImageResponseDTO> imageResponse = new ArrayList<>();

			ProductEntity product = componentService.getProductById(productId);

			String cloudinaryFolder = UPLOAD_DIR_BASE + userid + "/" + product.getCategory().toLowerCase() + "/"
					+ product.getId().toString();

			int displayOrder = 1;

			for (MultipartFile file : files) {
				if (!file.isEmpty()) {
					try {
						String publicId = product.getId().toString() + "_" + displayOrder + "_"
								+ System.currentTimeMillis();

						Map uploadResult = this.cloudinary.uploader().upload(file.getBytes(),
								ObjectUtils.asMap("folder", cloudinaryFolder, "public_id", publicId));

						String publicUrl = (String) uploadResult.get("secure_url");

						ProductImageEntity imageEntity = new ProductImageEntity();
						imageEntity.setUrl(publicUrl);
						imageEntity.setDisplayOrder(displayOrder++);
						imageEntity.setProduct(product);

						ProductImageResponseDTO imageResponseDTO = imageComponentService.save(imageEntity);
						imageResponse.add(imageResponseDTO);

					} catch (IOException e) {
						throw new RuntimeException("Failed to read image file stream for: " + file.getOriginalFilename()
								+ ". Error: " + e.getMessage(), e);
					} catch (Exception e) {
						throw new RuntimeException("Cloudinary upload failed for file: " + file.getOriginalFilename()
								+ ". Error: " + e.getMessage(), e);
					}
				}
			}
			return imageResponse;
		} else {
			throw new AccessUnauthorizedException("User: " + userid + " whith roles: " + roles + " is not authorized.");
		}
	}

	@Override
	public ProductResponseDTO findProductByIdWithChildren(Long id, String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			return ProductMapper.toDto(componentService.findProductByIdWithChildren(id));
		} else {
			throw new AccessUnauthorizedException("User: " + userid + " whith roles: " + roles + " is not authorized.");
		}
	}

	@Override
	public void deleteProduct(Long id, String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String now = LocalDateTime.now().format(dateFormat1);
			LocalDateTime current = LocalDateTime.parse(now, dateFormat1);

			ProductEntity entity = componentService.getProductById(id);
			entity.setDeletedFlag('Y');
			entity.setRecordEndDate(current);
			componentService.createProduct(entity);
		}
	}

	@Override
	public ProductResponseDTO updateProductDetails(Long id, ProductRequestDTO requestDTO, String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			ProductEntity productEntity = ProductMapper.toModel(requestDTO);
			//productEntity = componentService.findProductByIdWithChildren(id);
			
			DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
			String now = LocalDateTime.now().format(dateFormat1);
			LocalDateTime startDate = LocalDateTime.parse(now, dateFormat1);
			LocalDateTime endDate = LocalDateTime.parse(HIGH_DATE, dateFormat1);

			productEntity.setCreatedBy(userid);
			productEntity.setDeletedFlag('N');
			productEntity.setRecordStartDate(startDate);
			productEntity.setRecordEndDate(endDate);
			
			try {
				return ProductMapper.toDto(componentService.updateProductDetails(productEntity));
			} catch (Exception e) {
				throw new RuntimeException(e);
			}
		} else {
			throw new AccessUnauthorizedException("User: " + userid + " whith roles: " + roles + " is not authorized.");
		}
	}
}
