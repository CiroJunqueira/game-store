package com.game.gamestoreimpl.service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.game.gamestoreimpl.entity.Order;
import com.game.gamestoreimpl.entity.OrderItem;
import com.game.gamestoreimpl.entity.Product;
import com.game.gamestoreimpl.exception.ErrorException;
import com.game.gamestoreimpl.repository.OrderItemRepository;
import com.game.gamestoreimpl.repository.OrderRepository;
import com.game.gamestoreimpl.repository.ProductRepository;

@Service
public class OrderService {

	@Autowired
	private OrderRepository orderRepository;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private OrderItemRepository orderItemRepository;

	public OrderItem addItem(Long productCode, int quantity, Integer orderCode)  {
		Product product = productRepository.findByProductCode(productCode);
		if (product == null) {
			throw new ErrorException("code not found. ");
		}
		Order order = null;
		if (orderCode != null) {
			order = orderRepository.findByOrderCode(orderCode);
		}
		boolean fgNewOrder = false;
		if (order == null) {
			fgNewOrder = true;
			order = new Order();
		}
		OrderItem orderItem = new OrderItem(product, quantity);
		order.addItem(orderItem, fgNewOrder);
		orderItem.setOrder(order);
		orderRepository.save(order);
		return orderItem;
	}

	public void delete(Long productCode, int orderCode, int quantity)  {
		Order order = orderRepository.findByOrderCode(orderCode);
		if (order == null) {
			throw new ErrorException("Order code not found. ");
		}
		OrderItem orderItem = order.findProduct(productCode);
		if (orderItem == null) {
			throw new ErrorException("item code not found. ");
		}
		if (quantity >= orderItem.getQuantity()) {
			order.getItemsInCart().remove(orderItem);
			orderItemRepository.delete(orderItem);
		} else {
			orderItem.setQuantity(orderItem.getQuantity() - quantity);
			orderRepository.save(order);
		}
	}

	public List<Product> sort(int code, String sort)  {
		List<Product> products = new ArrayList<>();
		Order order = orderRepository.findByOrderCode(code);
		if (order == null) {
			throw new ErrorException("code not found. ");
		}
		order.getItemsInCart().forEach(item -> products.add(item.getProduct()));
		switch (sort) {
		case "price":
			products.sort(Comparator.comparing(Product::getPrice));
			break;
		case "score":
			products.sort(Comparator.comparing(Product::getScore));
			break;
		case "name":
			products.sort(Comparator.comparing(Product::getName));
			break;
		default:
			throw new ErrorException("Order not valid. ");
		}
		return products;

	}

	public Order findOrder(int code)  {
		Order order = orderRepository.findByOrderCode(code);
		if (order == null) {
			throw new ErrorException("code not found. ");
		}
		return order;
	}
}
