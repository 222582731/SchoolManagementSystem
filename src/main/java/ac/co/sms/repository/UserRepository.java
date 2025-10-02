package ac.co.sms.repository;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmailAndPassword(String email, String password);
    boolean existsByEmail(String email);
    List<User> findByUserType(UserType userType);
}
