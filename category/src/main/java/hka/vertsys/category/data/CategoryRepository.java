package hka.vertsys.category.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface CategoryRepository extends JpaRepository<Category, Integer>  {

	@Query("SELECT c FROM Category c WHERE c.name = ?1")
	Category findByName(String categoryName);
}
