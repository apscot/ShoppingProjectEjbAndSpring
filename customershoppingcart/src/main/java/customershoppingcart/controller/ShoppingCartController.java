package customershoppingcart.controller;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;

import com.fasterxml.jackson.core.exc.StreamReadException;
import com.fasterxml.jackson.databind.DatabindException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import customershoppingcart.cacheConfig.LocalInMemoryCache;
import customershoppingcart.entity.FinalReponseEntity;
import customershoppingcart.entity.Products;
import customershoppingcart.entity.ShoppingCart;
import customershoppingcart.entity.ShoppingCartFinalResponse;
import customershoppingcart.entity.ShoppingCartResponse;
import jakarta.ejb.LocalBean;
import jakarta.ejb.Schedule;
import jakarta.ejb.Schedules;
import jakarta.ejb.Stateless;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;

@Stateless
@LocalBean
@Path("/")
public class ShoppingCartController {
	LocalInMemoryCache<String, FinalReponseEntity> localInMemoryCache;

	/**
	 * Default constructor.
	 */
	public ShoppingCartController() {
		localInMemoryCache = new LocalInMemoryCache<>(30, 1, 1);
	}

	@Schedules({ @Schedule(hour = "*", minute = "*") })
	private void plantTheCorn() throws IOException {

		List<Integer> productIdList = Arrays.stream(new int[] { 10, 11, 12 }).boxed().collect(Collectors.toList());

		JSONObject jsonObjectString = new JSONObject();
		jsonObjectString.put("price", (long) ((Math.random() * (1000 - 500)) + 500));

		HttpURLConnection connection;
		String productsUri = "http://productproject:8090/products/"
				+ productIdList.get((int) ((Math.random() * (2 - 0)) + 0));

		URL productsUrl = new URL(productsUri);
		connection = (HttpURLConnection) productsUrl.openConnection();
		connection.setRequestMethod("PUT");
		connection.setDoOutput(true);
		connection.setRequestProperty("Produces", "application/JSON");
		connection.setRequestProperty("Accepts", "application/JSON");
		connection.setRequestProperty("Content-Type", "application/JSON");
		OutputStreamWriter outputStreamWriter = new OutputStreamWriter(connection.getOutputStream());
		outputStreamWriter.write(jsonObjectString.toString());
		outputStreamWriter.flush();
		int responseCode = connection.getResponseCode();
		System.out.println("Response Code for Scheduled Task is: " + responseCode);

		connection.disconnect();
	}

	@GET
	@Path("/{id}")
	@Produces("application/json")
	public FinalReponseEntity fetchShoppingCartById(@PathParam("id") String id) throws IOException, JAXBException {

		FinalReponseEntity finalReponseEntity = localInMemoryCache.get(id);
		if (finalReponseEntity != null)
			return finalReponseEntity;
		else {

			ShoppingCartResponse shoppingCartResponse = fetchShoppingCartAPI(id);

			List<Products> products = fetchProductsAPI();

			finalReponseEntity = generateFinalResponseEntity(shoppingCartResponse, products);
			localInMemoryCache.put(id, finalReponseEntity);

			return finalReponseEntity;
		}
	}

	private ShoppingCartResponse fetchShoppingCartAPI(String id)
			throws MalformedURLException, IOException, ProtocolException, JAXBException {
		String shoppingCartUri = "http://shoppingproject:9090/getShoppingCarts/" + id;
		URL shoppingCartUrl = new URL(shoppingCartUri);
		HttpURLConnection connection = (HttpURLConnection) shoppingCartUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Produces", "application/xml");

		JAXBContext jc = JAXBContext.newInstance(ShoppingCartResponse.class);
		InputStream xml = connection.getInputStream();
		ShoppingCartResponse shoppingCartResponse = (ShoppingCartResponse) jc.createUnmarshaller().unmarshal(xml);

		connection.disconnect();
		return shoppingCartResponse;
	}

	private List<Products> fetchProductsAPI()
			throws MalformedURLException, IOException, ProtocolException, StreamReadException, DatabindException {
		HttpURLConnection connection;
		String productsUri = "http://productproject:8090/products";
		URL productsUrl = new URL(productsUri);
		connection = (HttpURLConnection) productsUrl.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Produces", "application/JSON");
		InputStream json = connection.getInputStream();

		ObjectMapper objectMapper = new ObjectMapper();
		CollectionType collectionType = objectMapper.getTypeFactory().constructCollectionType(List.class,
				Products.class);

		List<Products> products = objectMapper.readValue(json, collectionType);

		connection.disconnect();
		return products;
	}

	private FinalReponseEntity generateFinalResponseEntity(ShoppingCartResponse shoppingCartResponse,
			List<Products> products) {
		long totalprice = 0;
		FinalReponseEntity finalReponseEntity = new FinalReponseEntity();
		List<ShoppingCartFinalResponse> shoppingCartFinalResponses = new ArrayList<ShoppingCartFinalResponse>();
		for (ShoppingCart shoppingCart : shoppingCartResponse.getShoppingCartList()) {
			ShoppingCartFinalResponse shoppingCartFinalResponse = new ShoppingCartFinalResponse();
			shoppingCartFinalResponse.setCustomerId(shoppingCart.getCustomerId());
			shoppingCartFinalResponse.setId(shoppingCart.getId());
			shoppingCartFinalResponse.setProductId(shoppingCart.getProductId());
			shoppingCartFinalResponse.setQuantity(shoppingCart.getQuantity());
			Products product = products.stream().filter(x -> x.getId() == shoppingCart.getProductId()).findFirst()
					.get();
			totalprice = totalprice + product.getPrice() * shoppingCart.getQuantity();
			shoppingCartFinalResponse.setPrice(product.getPrice());
			shoppingCartFinalResponse.setName(product.getName());
			shoppingCartFinalResponses.add(shoppingCartFinalResponse);
		}
		finalReponseEntity.setShoppingCarts(shoppingCartFinalResponses);
		finalReponseEntity.setTotalPrice(totalprice);
		return finalReponseEntity;
	}
}
