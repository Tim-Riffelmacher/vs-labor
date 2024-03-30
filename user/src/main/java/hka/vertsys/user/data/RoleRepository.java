package hka.vertsys.user.data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface RoleRepository extends JpaRepository<Role, Integer> {

	@Query("SELECT r FROM Role r WHERE r.level = ?1")
	Role findByLevel(int level);
}
