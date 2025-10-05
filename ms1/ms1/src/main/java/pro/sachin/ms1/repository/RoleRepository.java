package pro.sachin.ms1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sachin.ms1.model.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

}
