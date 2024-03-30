package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.microservice.connector.RestCallUtil;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.MicroserviceProduct;
import hska.iwi.eShopMaster.model.database.dataobjects.Product;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.reflect.TypeToken;

public class ProductManagerImpl implements ProductManager {

	private RestCallUtil restUtil;
	private RestCallUtil categoryUtil;

	public ProductManagerImpl() {
		restUtil = new RestCallUtil("http://product-microservice:8082/product");
		categoryUtil = new RestCallUtil("http://category-microservice:8081/category");
	}

	public List<Product> getProducts() {
		String requestUrl = "/all";
		Type listType = new TypeToken<List<MicroserviceProduct>>() {
		}.getType();
		List<MicroserviceProduct> products = restUtil.getObjectFromGetEndpoint(requestUrl, listType);
		List<Product> legacyProducts = new ArrayList<Product>();
		for (MicroserviceProduct product : products) {
			legacyProducts.add(transformTo(product));
		}
		return legacyProducts;
	}

	public List<Product> getProductsForSearchValues(String searchDescription, Double searchMinPrice,
			Double searchMaxPrice) {
		searchMinPrice = searchMinPrice == null ? Double.MIN_VALUE : searchMinPrice;
		searchMaxPrice = searchMaxPrice == null ? Double.MAX_VALUE : searchMaxPrice;
		searchDescription = searchDescription == null || searchDescription.isEmpty() ? "" : "/" + searchDescription;
		String requestUrl = "/filter/" + searchMinPrice + "/" + searchMaxPrice + searchDescription;
		Type listType = new TypeToken<List<MicroserviceProduct>>() {
		}.getType();
		List<MicroserviceProduct> products = restUtil.getObjectFromGetEndpoint(requestUrl, listType);
		List<Product> legacyProducts = new ArrayList<Product>();
		for (MicroserviceProduct product : products) {
			legacyProducts.add(transformTo(product));
		}
		return legacyProducts;
	}

	public Product getProductById(int id) {
		String requestUrl = "/byId/" + id;
		return transformTo(restUtil.getObjectFromGetEndpoint(requestUrl, MicroserviceProduct.class));
	}

	public Product getProductByName(String name) {
		String requestUrl = "/byName/" + name;
		return transformTo(restUtil.getObjectFromGetEndpoint(requestUrl, MicroserviceProduct.class));
	}

	public int addProduct(String name, double price, int categoryId, String details) {
		String requestUrl = "/add";
		MicroserviceProduct newProduct = new MicroserviceProduct(name, price, categoryId, details);
		return restUtil.getObjectFromPostEndpoint(requestUrl, newProduct, MicroserviceProduct.class, Integer.class);
	}

	public void deleteProductById(int id) {
		String requestUrl = "/deleteById/" + id;
		restUtil.getObjectFromGetEndpoint(requestUrl, null);
	}

	public boolean deleteProductsByCategoryId(int categoryId) {
		String requestUrl = "/deleteByCategoryId/" + categoryId;
		restUtil.getObjectFromGetEndpoint(requestUrl, null);
		return false;
	}

	private Product transformTo(MicroserviceProduct product) {
		Product p = new Product();
		p.setId(product.getId());
		p.setName(product.getName());
		p.setPrice(product.getPrice());
		p.setDetails(product.getDetails());
		p.setCategory(getCategory(product.getCategory()));
		return p;
	}

	private Category getCategory(int id) {
		String requestUrl = "/byId/" + id;
		return categoryUtil.getObjectFromGetEndpoint(requestUrl, Category.class);
	}

}
