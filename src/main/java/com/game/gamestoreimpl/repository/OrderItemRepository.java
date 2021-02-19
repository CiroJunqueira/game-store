package com.game.gamestoreimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.gamestoreimpl.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem, Long>{

	
}
