package com.example.carts.service;

import java.util.List;

import com.example.carts.entity.ShoppingCart;

public interface ShoppingCartService {
	
	List<ShoppingCart> getShoppingCartById(int customerId);

}
