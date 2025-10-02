package ac.co.sms.controller;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import ac.co.sms.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User user) {
        System.out.println("Attempting to register user: " + user); // debug input

        try {
            User newUser = userService.register(user);
            System.out.println("Registration successful: " + newUser); // debug success
            return ResponseEntity.status(HttpStatus.CREATED).body(newUser);
        } catch (IllegalArgumentException e) {
            System.err.println("Registration failed: " + e.getMessage()); // debug specific error
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        } catch (Exception e) {
            System.err.println("Unexpected server error: " + e.getMessage()); // debug unexpected errors
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }



    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody User loginRequest) {
        Optional<User> user = userService.login(loginRequest.getEmail(), loginRequest.getPassword());

        return user.<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                        .body(Map.of("error", "Invalid email or password")));
    }

    
    @PostMapping("/create")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User savedUser = userService.save(user);
        return savedUser != null
                ? new ResponseEntity<>(savedUser, HttpStatus.CREATED)
                : ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/all")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.findAll());
    }

    @GetMapping("/read/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        User user = userService.read(id);
        return user != null ? ResponseEntity.ok(user) : ResponseEntity.notFound().build();
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User user) {
        user.setUserId(id);
        try {
            return ResponseEntity.ok(userService.update(user));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteById(id);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/type/{userType}")
    public ResponseEntity<List<User>> getUsersByType(@PathVariable UserType userType) {
        return ResponseEntity.ok(userService.findByUserType(userType));
    }
}
