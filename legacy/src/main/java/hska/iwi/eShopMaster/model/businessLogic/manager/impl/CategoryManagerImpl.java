package hska.iwi.eShopMaster.model.businessLogic.manager.impl;

import hska.iwi.eShopMaster.model.businessLogic.manager.CategoryManager;
import hska.iwi.eShopMaster.model.businessLogic.manager.ProductManager;
import hska.iwi.eShopMaster.model.businessLogic.microservice.connector.RestCallUtil;
import hska.iwi.eShopMaster.model.database.dataobjects.Category;
import hska.iwi.eShopMaster.model.database.dataobjects.MicroserviceCategory;

import java.util.List;

import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;

public class CategoryManagerImpl implements CategoryManager {

	private RestCallUtil restUtil;
	private ProductManager productManager;

	public CategoryManagerImpl() {
		restUtil = new RestCallUtil("http://category:8081/category");
		productManager = new ProductManagerImpl();
	}

	public List<Category> getCategories() {
		String requestUrl = "/all";
		Type listType = new TypeToken<List<Category>>(){}.getType();
		return restUtil.getObjectFromGetEndpoint(requestUrl, listType);
	}

	public Category getCategory(int id) {
		String requestUrl = "/byId/" + id;
		return restUtil.getObjectFromGetEndpoint(requestUrl, Category.class);
	}

	public Category getCategoryByName(String name) {
		String requestUrl = "/byName/" + name;
		return restUtil.getObjectFromGetEndpoint(requestUrl, Category.class);
	}

	public void addCategory(String name) {
		String requestUrl = "/add";
		MicroserviceCategory cat = new MicroserviceCategory(name);
		restUtil.getObjectFromPostEndpoint(requestUrl, cat, MicroserviceCategory.class, null);
	}

	public void delCategory(Category cat) {
		String requestUrl = "/delete/" + cat;
		restUtil.getObjectFromGetEndpoint(requestUrl, null);
		productManager.deleteProductsByCategoryId(cat.getId());
	}

	public void delCategoryById(int id) {
		String requestUrl = "/deleteById/" + id;
		restUtil.getObjectFromGetEndpoint(requestUrl, null);
		productManager.deleteProductsByCategoryId(id);
	}
}
