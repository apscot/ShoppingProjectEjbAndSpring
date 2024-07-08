package com.example.carts.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.carts.entity.Response;
import com.example.carts.service.ShoppingCartService;

@RestController
@RequestMapping(produces = "application/xml")
public class ShoppingCartController {

	@Autowired
	private ShoppingCartService shoppingCartService;

	@GetMapping("/getShoppingCarts/{customerId}")
	public ResponseEntity<Response> findCartByCustomerId(@PathVariable(value="customerId") String customerId,Response response) {
		response.setShoppingCartList(shoppingCartService.getShoppingCartById(Integer.parseInt(customerId)));
		HttpHeaders headers = new HttpHeaders();
		return ResponseEntity.ok().headers(headers).body(response);
	}

}
