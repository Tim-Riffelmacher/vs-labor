package hka.vertsys.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Integer> {
	
	@Query("SELECT c FROM User c WHERE c.username = ?1")
	User findByUsername(String username);

}
