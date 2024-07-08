package com.example.carts.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.carts.entity.ShoppingCart;

public interface ShoppingCartRepository extends JpaRepository<ShoppingCart, Integer>{
	
	public List<ShoppingCart> findByCustomerId(int customerId);

}
