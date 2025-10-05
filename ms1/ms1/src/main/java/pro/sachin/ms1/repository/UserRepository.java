package pro.sachin.ms1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pro.sachin.ms1.model.User;
@Repository
public interface UserRepository extends JpaRepository<User,Long>{
    User findByEmail(String email);
}
