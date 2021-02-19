package com.game.gamestoreimpl.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "tb_order_item")
public class OrderItem implements Serializable {
	private static final long serialVersionUID = 1L;

	@JsonIgnore
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@ManyToOne()
	@JoinColumn(name = "product_id")
	private Product product;
	
	private int quantity;
	
	private Integer orderCode;

	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order order;

	public double getSubTotal() {
		return product.getPrice().doubleValue() * quantity;
	}

	public OrderItem(Product product, int quantity) {
		this.product = product;
		this.quantity = quantity;
	}

}
