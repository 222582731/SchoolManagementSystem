package ac.co.sms.controller;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static User testUser;

    @Test
    @Order(1)
    void testRegisterUser() {
        testUser = new User();
        testUser.setName("Test User");
        testUser.setEmail("testuser@example.com");
        testUser.setPassword("test123");
        testUser.setUserType(UserType.STUDENT);

        ResponseEntity<User> response = restTemplate.postForEntity(
                "/user/register", testUser, User.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getUserId());

        testUser = response.getBody(); // store user for login
        System.out.println("Registered user: " + testUser);
    }

    @Test
    @Order(2)
    void testLoginUser() {
        User loginRequest = new User();
        loginRequest.setEmail(testUser.getEmail());
        loginRequest.setPassword(testUser.getPassword());

        ResponseEntity<User> response = restTemplate.postForEntity(
                "/user/login", loginRequest, User.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testUser.getEmail(), response.getBody().getEmail());

        System.out.println("Logged in user: " + response.getBody());
    }

    @Test
    @Order(3)
    void testLoginWithInvalidPassword() {
        User loginRequest = new User();
        loginRequest.setEmail(testUser.getEmail());
        loginRequest.setPassword("wrongPassword");

        ResponseEntity<Map> response = restTemplate.postForEntity(
                "/user/login", loginRequest, Map.class
        );

        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
        assertTrue(response.getBody().containsKey("error"));
        System.out.println("Invalid login attempt: " + response.getBody());
    }
}
