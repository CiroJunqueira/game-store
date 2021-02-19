package com.game.gamestoreimpl.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.game.gamestoreimpl.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long>{

	Product findByProductCode(long productCode);
}
