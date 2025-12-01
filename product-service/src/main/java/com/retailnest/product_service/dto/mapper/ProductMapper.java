package com.retailnest.product_service.dto.mapper;

import java.util.stream.Collectors;

import com.retailnest.product_service.dto.ProductBulletGroupResponseDTO;
import com.retailnest.product_service.dto.ProductBulletPointResponseDTO;
import com.retailnest.product_service.dto.ProductImageResponseDTO;
import com.retailnest.product_service.dto.ProductRequestDTO;
import com.retailnest.product_service.dto.ProductResponseDTO;
import com.retailnest.product_service.entity.ProductBulletGroupEntity;
import com.retailnest.product_service.entity.ProductBulletPointEntity;
import com.retailnest.product_service.entity.ProductEntity;
import com.retailnest.product_service.entity.ProductImageEntity;

public class ProductMapper {

	public static ProductEntity toModel(ProductRequestDTO dto) {
		ProductEntity entity = new ProductEntity();
		entity.setName(dto.getName());
		entity.setBrand(dto.getBrand());
		entity.setCategory(dto.getCategory());
		entity.setPrice(Double.parseDouble(dto.getPrice()));
		entity.setAbout(dto.getAbout());
		entity.setSku(dto.getSku());

		if (dto.getBulletGroups() != null) {
			entity.setBulletGroups(dto.getBulletGroups().stream().map(bgDto -> {
				ProductBulletGroupEntity group = new ProductBulletGroupEntity();
				group.setHeading(bgDto.getHeading());
				group.setDisplayOrder(bgDto.getDisplayOrder());
				group.setProduct(entity);

				if (bgDto.getBulletPoints() != null) {
					group.setBulletPoints(bgDto.getBulletPoints().stream().map(bpDto -> {
						ProductBulletPointEntity point = new ProductBulletPointEntity();
						point.setPointText(bpDto.getPointText());
						point.setDisplayOrder(bpDto.getDisplayOrder());
						point.setGroup(group);
						return point;
					}).collect(Collectors.toSet()));
				}
				return group;
			}).collect(Collectors.toSet()));
		}

		if (dto.getImages() != null) {
			entity.setImages(dto.getImages().stream().map(imgDto -> {
				ProductImageEntity img = new ProductImageEntity();
				img.setUrl(imgDto.getUrl());
				img.setDisplayOrder(imgDto.getDisplayOrder());
				img.setProduct(entity);
				return img;
			}).collect(Collectors.toSet()));
		}

		return entity;
	}

	public static ProductResponseDTO toDto(ProductEntity entity) {
		ProductResponseDTO dto = new ProductResponseDTO();
		dto.setId(entity.getId().toString());
		dto.setName(entity.getName());
		dto.setBrand(entity.getBrand());
		dto.setCategory(entity.getCategory());
		dto.setPrice(entity.getPrice().toString());
		dto.setAbout(entity.getAbout());
		dto.setSku(entity.getSku());
		dto.setRecordStartDate(entity.getRecordStartDate().toString());
		dto.setRecordEndDate(entity.getRecordEndDate().toString());
		dto.setDeletedFlag(entity.getDeletedFlag().toString());
		dto.setCreatedBy(entity.getCreatedBy());

		if (entity.getBulletGroups() != null) {
			dto.setBulletGroups(entity.getBulletGroups().stream().map(group -> {
				ProductBulletGroupResponseDTO bgDto = new ProductBulletGroupResponseDTO();
				bgDto.setId(group.getId().toString());
				bgDto.setHeading(group.getHeading());
				bgDto.setDisplayOrder(group.getDisplayOrder());

				if (group.getBulletPoints() != null) {
					bgDto.setBulletPoints(group.getBulletPoints().stream().map(point -> {
						ProductBulletPointResponseDTO bpDto = new ProductBulletPointResponseDTO();
						bpDto.setId(point.getId().toString());
						bpDto.setPointText(point.getPointText());
						bpDto.setDisplayOrder(point.getDisplayOrder());
						return bpDto;
					}).collect(Collectors.toList()));
				}
				return bgDto;
			}).collect(Collectors.toList()));
		}

		if (entity.getImages() != null) {
			dto.setImages(entity.getImages().stream().map(img -> {
				ProductImageResponseDTO imgDto = new ProductImageResponseDTO();
				imgDto.setId(img.getId().toString());
				imgDto.setUrl(img.getUrl());
				imgDto.setDisplayOrder(img.getDisplayOrder());
				imgDto.setProductId(img.getProduct().getId().toString());
				return imgDto;
			}).collect(Collectors.toList()));
		}

		return dto;
	}
	
	public static ProductImageResponseDTO toDto(ProductImageEntity entity) {
        ProductImageResponseDTO dto = new ProductImageResponseDTO();
        if (entity.getId() != null) {
             dto.setId(entity.getId().toString());
        }
        dto.setUrl(entity.getUrl());
        dto.setDisplayOrder(entity.getDisplayOrder());
        dto.setProductId(entity.getProduct().getId().toString());
        return dto;
    }
}
