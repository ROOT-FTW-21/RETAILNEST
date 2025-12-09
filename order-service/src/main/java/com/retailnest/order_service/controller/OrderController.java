package com.retailnest.order_service.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.retailnest.order_service.dto.OrderRequestDTO;
import com.retailnest.order_service.dto.OrderResponseDTO;
import com.retailnest.order_service.service.IOrderService;

@RestController
@RequestMapping("/order")
public class OrderController {
	@Autowired
	private IOrderService orderService;
	
	@PostMapping(name = "/place-order", consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<OrderResponseDTO> placeOrder(@RequestHeader("X-UserId") String userid,
			@RequestHeader("X-Roles") String roles, @RequestBody OrderRequestDTO requestDTO) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.placeOrder(userid, roles, requestDTO));
	}
	
	@GetMapping(name = "/get-all-orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderResponseDTO>> getAllOrders(@RequestHeader("X-UserId") String userid,
			@RequestHeader("X-Roles") String roles) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getAllOrders(userid, roles));
	}
	
	@GetMapping(name = "/get-user-orders", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<OrderResponseDTO>> getUserOrders(@RequestHeader("X-UserId") String userid) {
		return ResponseEntity.status(HttpStatus.OK).body(orderService.getUserOrders(userid));
	}
}	
