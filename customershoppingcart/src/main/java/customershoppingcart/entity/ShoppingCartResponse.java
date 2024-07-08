package customershoppingcart.entity;

import java.util.List;

import jakarta.xml.bind.annotation.XmlAccessType;
import jakarta.xml.bind.annotation.XmlAccessorType;
import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "root")
@XmlAccessorType(XmlAccessType.FIELD)
public class ShoppingCartResponse {
	@XmlElement(name ="row")
	List<ShoppingCart> shoppingCartList;
	
	
	public List<ShoppingCart> getShoppingCartList() {
		return shoppingCartList;
	}

	public void setShoppingCartList(List<ShoppingCart> shoppingCartList) {
		this.shoppingCartList = shoppingCartList;
	}
	

}
