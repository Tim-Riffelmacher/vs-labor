package hka.vertsys.product.data;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;


public interface ProductRepository extends JpaRepository<Product, Integer> {

	@Query("SELECT p FROM Product p WHERE p.name = ?1")
	Product findByName(String productName);
	
	@Modifying
    @Transactional
	@Query("DELETE FROM Product p WHERE p.category = ?1")
	void deleteByCategoryId(int categoryId);

	@Query("SELECT p FROM Product p WHERE (?1 = '' OR p.details like ?1) AND p.price BETWEEN ?2 AND ?3")
	List<Product> getProductsForSearchValues(String searchValue, Double searchMinPrice, Double searchMaxPrice);
	
}
