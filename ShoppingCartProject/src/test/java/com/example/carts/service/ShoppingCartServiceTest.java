package com.example.carts.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.carts.entity.ShoppingCart;
import com.example.carts.repository.ShoppingCartRepository;

@ExtendWith(MockitoExtension.class)
class ShoppingCartServiceTest {

	@Mock
	ShoppingCartRepository shoppingCartRepository;
	@InjectMocks
	ShoppingCartServiceImpl shoppingCartService;

	@Test
	void getShoppingCartByIdTest_Pass() {

		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCustomerId(1);
		shoppingCart.setId(1);
		shoppingCart.setProductId(1);
		shoppingCart.setQuantity(1);
		List<ShoppingCart> shoppingCarts = new ArrayList<>();
		shoppingCarts.add(shoppingCart);

		when(shoppingCartRepository.findByCustomerId(1)).thenReturn(shoppingCarts);
		assertEquals(shoppingCarts, shoppingCartService.getShoppingCartById(1));
	}

	@Test
	void getShoppingCartByIdTest_Fail() {

		when(shoppingCartRepository.findByCustomerId(1)).thenThrow(RuntimeException.class);
		assertThrows(RuntimeException.class, () -> shoppingCartService.getShoppingCartById(1));
	}

}
