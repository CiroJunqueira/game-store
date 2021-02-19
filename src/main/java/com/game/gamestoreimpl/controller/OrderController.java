package com.game.gamestoreimpl.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.game.gamestoreimpl.entity.Order;
import com.game.gamestoreimpl.entity.OrderItem;
import com.game.gamestoreimpl.entity.Product;
import com.game.gamestoreimpl.service.OrderService;

@RestController
@RequestMapping(value = "/order")
public class OrderController {

	@Autowired
	private OrderService service;

	@PostMapping
	public ResponseEntity<OrderItem> addInCart(@RequestParam(name = "productCode", required = true) Long productCode,
			@RequestParam(name = "quantity", required = true) int quantity,
			@RequestParam(name = "orderCode", required = false) Integer orderCode)  {
		OrderItem orderItem = service.addItem(productCode, quantity, orderCode);
		return ResponseEntity.ok().body(orderItem);
	}

	@DeleteMapping("/{orderCode}")
	public ResponseEntity<?> removeFromCart(@PathVariable int orderCode,
			@RequestParam(name = "productCode", required = true) long productCode, @RequestParam(name = "quantity", required = true) int quantity)  {
		service.delete(productCode, orderCode, quantity);
		return ResponseEntity.noContent().build();
	}

	@GetMapping
	public ResponseEntity<List<Product>> sortProduct(@RequestParam(name = "sort", required = true) String sort,
			@RequestParam(name = "orderCode", required = true) int orderCode)  {
		List<Product> products = service.sort(orderCode, sort);
		return ResponseEntity.ok().body(products);
	}

	@GetMapping("/{orderCode}")
	public ResponseEntity<Order> checkout(@PathVariable int orderCode) {
		Order order = service.findOrder(orderCode);
		return ResponseEntity.ok().body(order);
	}

}
