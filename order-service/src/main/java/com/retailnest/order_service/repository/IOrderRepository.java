package com.retailnest.order_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.retailnest.order_service.dto.OrderResponseDTO;
import com.retailnest.order_service.entity.OrderEntity;

@Repository
public interface IOrderRepository extends JpaRepository<OrderEntity, Long> {

	List<OrderResponseDTO> findByUserid(String userid);

}
