package com.retailnest.order_service.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retailnest.order_service.dto.OrderRequestDTO;
import com.retailnest.order_service.dto.OrderResponseDTO;
import com.retailnest.order_service.dto.mapper.OrderMapper;
import com.retailnest.order_service.entity.OrderEntity;
import com.retailnest.order_service.entity.constants.EOrderStatus;
import com.retailnest.order_service.entity.constants.EPaymentStatus;
import com.retailnest.order_service.repository.IOrderRepository;
import com.retailnest.order_service.service.IOrderService;
import com.retailnest.order_service.exception.AccessUnauthorizedException;

@Service
public class OrderServiceImpl implements IOrderService {
	private static final String ROLE_ADMIN = "ADMIN";
	private static final String ROLE_MAINTAINANCE = "MAINTAINANCE";
	private static final String ROLE_USER = "USER";

	@Autowired
	private IOrderRepository orderRepository;

	@Override
	public OrderResponseDTO placeOrder(String userid, String roles, OrderRequestDTO requestDTO) {
		DateTimeFormatter dateFormat1 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
		String now = LocalDateTime.now().format(dateFormat1);
		LocalDateTime orderDate = LocalDateTime.parse(now, dateFormat1);

		OrderEntity orderEntity = OrderMapper.toEntity(requestDTO);
		orderEntity.setDeletedFlag('N');
		orderEntity.setOrderStatus(EOrderStatus.PENDING.getLabel());
		orderEntity.setOrderDateTime(orderDate);
		orderEntity.setPaymentStatus(EPaymentStatus.PENDING.getLabel());

		return OrderMapper.toDto(orderRepository.save(orderEntity));
	}

	@Override
	@Cacheable(value = "AllOrders")
	public List<OrderResponseDTO> getAllOrders(String userid, String roles) {
		List<String> roleList = Arrays.asList(roles.split(","));

		if (roleList.contains(ROLE_ADMIN) || roleList.contains(ROLE_MAINTAINANCE)) {
			return orderRepository.findAll().stream().filter(Objects::nonNull).map(order -> OrderMapper.toDto(order))
					.collect(Collectors.toList());
		} else {
			throw new AccessUnauthorizedException("User: " + userid + " whith roles: " + roles + " is not authorized.");
		}
	}

	@Override
	@Cacheable(value = "OrdersByUserId", key = "#userid")
	public List<OrderResponseDTO> getUserOrders(String userid) {
		return orderRepository.findByUserid(userid);
	}
	
	

}
