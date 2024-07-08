package com.example.carts.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.carts.entity.ShoppingCart;
import com.example.carts.repository.ShoppingCartRepository;

@Service
public class ShoppingCartServiceImpl implements ShoppingCartService {
	
	@Autowired
	ShoppingCartRepository shoppingCartRepository;

	@Override
	public List<ShoppingCart> getShoppingCartById(int customerId) {
		return shoppingCartRepository.findByCustomerId(customerId);
	}

}
