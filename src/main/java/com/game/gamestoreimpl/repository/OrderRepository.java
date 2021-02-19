package com.game.gamestoreimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.gamestoreimpl.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Long>{
	
	Order findByOrderCode(int orderCode);
}
