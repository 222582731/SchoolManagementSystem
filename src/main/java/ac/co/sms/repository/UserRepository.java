package ac.co.sms.repository;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);
    List<User> findByUserType(UserType userType);
    boolean existsByEmail(String email);

}
