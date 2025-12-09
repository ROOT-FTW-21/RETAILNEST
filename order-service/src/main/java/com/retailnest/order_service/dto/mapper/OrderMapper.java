package com.retailnest.order_service.dto.mapper;

import java.math.BigDecimal;

import com.retailnest.order_service.dto.OrderRequestDTO;
import com.retailnest.order_service.dto.OrderResponseDTO;
import com.retailnest.order_service.entity.OrderEntity;

public class OrderMapper {
	public static OrderEntity toEntity(OrderRequestDTO requestDTO) {
		OrderEntity entity = new OrderEntity();
		entity.setUserid(requestDTO.getUserid());
		entity.setProductId(Long.parseLong(requestDTO.getProductId()));
		entity.setOrderQuantity(Integer.parseInt(requestDTO.getOrderQuantity()));
		entity.setAmount(convertStringToBigDecimal(requestDTO.getAmount()));
		entity.setImageURL(requestDTO.getImageURL());
		entity.setPaymentType(requestDTO.getPaymentType());

		return entity;
	}

	public static OrderResponseDTO toDto(OrderEntity entity) {
		OrderResponseDTO dto = new OrderResponseDTO();
		dto.setId(entity.getId().toString());
		dto.setUserid(entity.getUserid());
		dto.setProductId(entity.getProductId().toString());
		dto.setOrderQuantity(entity.getOrderQuantity().toString());
		dto.setAmount(entity.getAmount().toString());
		dto.setImageURL(entity.getImageURL());
		dto.setDeletedFlag(entity.getDeletedFlag().toString());
		dto.setOrderStatus(entity.getOrderStatus());
		dto.setOrderDateTime(entity.getOrderDateTime().toString());
		dto.setPaymentStatus(entity.getPaymentStatus());
		dto.setPaymentType(entity.getPaymentType());

		return dto;
	}

	private static BigDecimal convertStringToBigDecimal(String amount) {
		BigDecimal bigDecimalAmount = new BigDecimal(amount);
		return bigDecimalAmount;
	}
}
