package customershoppingcart;

import java.util.HashSet;
import java.util.Set;

import customershoppingcart.controller.ShoppingCartController;
import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;

@ApplicationPath("/")
public class ShoppingCartMainApplication extends Application{
	  @Override
	    public Set<Class<?>> getClasses() {
	        Set<Class<?>> classes = new HashSet<Class<?>>();
	        classes.add(ShoppingCartController.class);
	        return classes;
	    }
	 
}
