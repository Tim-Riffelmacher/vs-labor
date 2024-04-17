package hka.vertsys.category.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import hka.vertsys.category.data.Category;
import hka.vertsys.category.data.CategoryRepository;
import hka.vertsys.category.data.RestCallUtil;

@RestController
@RequestMapping("/category")
public class CategoryController {

	@Autowired
	private CategoryRepository categoryRepo;

	@GetMapping("/all")
	public ResponseEntity<List<Category>> getCategories() {
		return new ResponseEntity<>(this.categoryRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/byId/{id}")
	public ResponseEntity<Category> getCategoryById(@PathVariable("id") Integer categoryId) {
		return new ResponseEntity<>(this.categoryRepo.findById(categoryId).orElse(new Category()), HttpStatus.OK);
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<Category> getCategoryByName(@PathVariable("name") String categoryName) {
		return new ResponseEntity<>(this.categoryRepo.findByName(categoryName), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addCategory(@RequestBody() Category categoryToAdd) {
		categoryRepo.save(categoryToAdd);
		return new ResponseEntity<>("added Category", HttpStatus.OK);
	}

	@PostMapping("/delete")
	public ResponseEntity<?> deleteCategory(@RequestBody() Category categoryToDelete) {
		this.categoryRepo.delete(categoryToDelete);
		deleteProductsByCategoryId(categoryToDelete.getId());
		return new ResponseEntity<>("deleted Category", HttpStatus.OK);
	}

	@GetMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteCategoryById(@PathVariable("id") Integer categoryId) {
		this.categoryRepo.deleteById(categoryId);
		deleteProductsByCategoryId(categoryId);
		return new ResponseEntity<>("deleted Category", HttpStatus.OK);
	}
	
	private void deleteProductsByCategoryId(int categoryId) {
		RestCallUtil restUtil = new RestCallUtil("http://product:8082/product");
		String requestUrl = "/deleteByCategoryId/" + categoryId;
		restUtil.getObjectFromGetEndpoint(requestUrl, null);
	}

}
