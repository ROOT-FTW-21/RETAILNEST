package com.retailnest.order_service.service;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.retailnest.order_service.dto.OrderRequestDTO;
import com.retailnest.order_service.dto.OrderResponseDTO;

@Service
public interface IOrderService {

	OrderResponseDTO placeOrder(String userid, String roles, OrderRequestDTO requestDTO);

	List<OrderResponseDTO> getAllOrders(String userid, String roles);

	List<OrderResponseDTO> getUserOrders(String userid);

}
