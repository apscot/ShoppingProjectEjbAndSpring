package com.example.carts.controller;

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
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.carts.entity.Response;
import com.example.carts.entity.ShoppingCart;
import com.example.carts.service.ShoppingCartService;

@ExtendWith(MockitoExtension.class)
class ShoppingCartControllerTest {

	@Mock
	ShoppingCartService shoppingCartService;

	@InjectMocks
	ShoppingCartController shoppingCartController;

	@Test
	void findCartByCustomerId_Pass() {
		ShoppingCart shoppingCart = new ShoppingCart();
		shoppingCart.setCustomerId(1);
		shoppingCart.setId(1);
		shoppingCart.setProductId(1);
		shoppingCart.setQuantity(1);
		List<ShoppingCart> shoppingCarts = new ArrayList<>();
		shoppingCarts.add(shoppingCart);

		when(shoppingCartService.getShoppingCartById(1)).thenReturn(shoppingCarts);

		ResponseEntity<Response> response = shoppingCartController.findCartByCustomerId("1", new Response());

		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals(shoppingCarts, response.getBody().getShoppingCartList());

	}
	
	@Test
	void findCartByCustomerId_Fail() {

		when(shoppingCartService.getShoppingCartById(1)).thenThrow(RuntimeException.class);
	
		assertThrows(RuntimeException.class, ()->shoppingCartController.findCartByCustomerId("1", new Response()));

	}

}
