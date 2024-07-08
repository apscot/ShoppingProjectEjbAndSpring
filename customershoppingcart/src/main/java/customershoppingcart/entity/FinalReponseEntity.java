package customershoppingcart.entity;

import java.util.List;

public class FinalReponseEntity {
	
	private long totalPrice;
	List<ShoppingCartFinalResponse> shoppingCarts;
	public long getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(long totalPrice) {
		this.totalPrice = totalPrice;
	}
	public List<ShoppingCartFinalResponse> getShoppingCarts() {
		return shoppingCarts;
	}
	public void setShoppingCarts(List<ShoppingCartFinalResponse> shoppingCarts) {
		this.shoppingCarts = shoppingCarts;
	}

}
