package com.game.gamestoreimpl.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order")
public class Order implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "order_id")
	private Long orderId;

	private double total;

	private Long shipping = 0L;

	@Column(name = "order_code")
	private Integer orderCode;

	@OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<OrderItem> itemsInCart = new ArrayList<>();

	public void addItem(OrderItem orderItem, boolean fgNewOrder) {
		OrderItem oi = findProduct(orderItem.getProduct().getProductCode());
		if (oi != null) {
			oi.setQuantity(oi.getQuantity() + orderItem.getQuantity());
		} else {
			itemsInCart.add(orderItem);
		}
		total += orderItem.getSubTotal();
		if (this.total >= 250L) {
			shipping = 0L;
		} else {
			shipping += 10L * orderItem.getQuantity();
		}
		if (fgNewOrder) {
			orderCode = ThreadLocalRandom.current().nextInt(1, 1000);
		}
		orderItem.setOrderCode(orderCode);
	}

	public OrderItem findProduct(Long productCode) {
		return itemsInCart.stream().filter(item -> item.getProduct().getProductCode().equals(productCode)).findAny()
				.orElse(null);
	}

}
