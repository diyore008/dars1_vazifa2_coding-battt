package uz.pdp.dars1_vazifa2_codingbat.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.dars1_vazifa2_codingbat.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
    boolean existsByEmail(String email);
}
