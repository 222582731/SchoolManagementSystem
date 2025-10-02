package ac.co.sms.service;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import ac.co.sms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService implements IUserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User entity) {
        return userRepository.save(entity);
    }

    @Override
    public User read(Long userId) {
        return userRepository.findById(userId).orElse(null);
    }

    @Override
    public User update(User entity) {
        if (userRepository.existsById(entity.getUserId())) {
            return userRepository.save(entity);
        }
        throw new IllegalArgumentException("User not found with id: " + entity.getUserId());
    }

    @Override
    public List<User> findByUserType(UserType userType) {
        return userRepository.findByUserType(userType);
    }

    @Override
    public List<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public void deleteById(Long userId) {
        if (userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
        } else {
            throw new IllegalArgumentException("User with ID " + userId + " not found");
        }
    }

    // --- NEW: Login method ---
    public Optional<User> login(String email, String password) {
        return userRepository.findAll().stream()
                .filter(u -> u.getEmail().equalsIgnoreCase(email) && u.getPassword().equals(password))
                .findFirst();
    }

    // --- NEW: Register method ---
    public User register(User user) {
        // Optional: check if email already exists
        boolean exists = userRepository.findAll().stream()
                .anyMatch(u -> u.getEmail().equalsIgnoreCase(user.getEmail()));

        if (exists) {
            throw new IllegalArgumentException("Email already registered");
        }

        return userRepository.save(user);
    }
}
