package hka.vertsys.product.controller;

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

import hka.vertsys.product.data.Product;
import hka.vertsys.product.data.ProductRepository;

@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@GetMapping("/all")
	public ResponseEntity<List<Product>> getCategories() {
		return new ResponseEntity<>(this.productRepo.findAll(), HttpStatus.OK);
	}

	@GetMapping("/byId/{id}")
	public ResponseEntity<Product> getProductById(@PathVariable("id") Integer productId) {
		return new ResponseEntity<>(this.productRepo.findById(productId).orElse(new Product()), HttpStatus.OK);
	}

	@GetMapping("/byName/{name}")
	public ResponseEntity<Product> getProductByName(@PathVariable("name") String productName) {
		return new ResponseEntity<>(this.productRepo.findByName(productName), HttpStatus.OK);
	}

	@PostMapping("/add")
	public ResponseEntity<Integer> addProduct(@RequestBody() Product productToAdd) {
		return new ResponseEntity<>(productRepo.save(productToAdd).getId(), HttpStatus.OK);
	}

	@GetMapping("/filter/{searchMinPrice}/{searchMaxPrice}/{searchValue}")
	public ResponseEntity<List<Product>> getProductsForSearchValues(@PathVariable Double searchMinPrice,
			@PathVariable Double searchMaxPrice, @PathVariable String searchValue) {
		String currentSearchValue = searchValue == null ? "" : "%" + searchValue + "%";
		return new ResponseEntity<>(
				this.productRepo.getProductsForSearchValues(currentSearchValue, searchMinPrice, searchMaxPrice),
				HttpStatus.OK);
	}
	
	@GetMapping("/filter/{searchMinPrice}/{searchMaxPrice}")
	public ResponseEntity<List<Product>> getProductsForSearchValues(@PathVariable Double searchMinPrice,
			@PathVariable Double searchMaxPrice) {
		String currentSearchValue = "";
		return new ResponseEntity<>(
				this.productRepo.getProductsForSearchValues(currentSearchValue, searchMinPrice, searchMaxPrice),
				HttpStatus.OK);
	}

	@GetMapping("/deleteByCategoryId/{categoryId}")
	public ResponseEntity<?> deleteByCategoryId(@PathVariable("categoryId") Integer categoryId) {
		this.productRepo.deleteByCategoryId(categoryId);
		return new ResponseEntity<>("deleted Product", HttpStatus.OK);
	}

	@GetMapping("/deleteById/{id}")
	public ResponseEntity<?> deleteProductById(@PathVariable("id") Integer productId) {
		this.productRepo.deleteById(productId);
		return new ResponseEntity<>("deleted Product", HttpStatus.OK);
	}

}
