package ac.co.sms.controller;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class UserControllerIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static User testUser;

    @BeforeAll
    static void setup() {
        testUser = new User("Integration User",
                "integration@example.com",
                "test123",
                UserType.STUDENT);
    }

    @Test
    @Order(1)
    void testRegisterUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<User> request = new HttpEntity<>(testUser, headers);

        ResponseEntity<User> response = restTemplate.postForEntity("/user/register", request, User.class);

        Assertions.assertEquals(HttpStatus.CREATED, response.getStatusCode());

        User createdUser = response.getBody();
        Assertions.assertNotNull(createdUser);
        Assertions.assertEquals(testUser.getName(), createdUser.getName());
        Assertions.assertEquals(testUser.getEmail(), createdUser.getEmail());
        Assertions.assertEquals(testUser.getUserType(), createdUser.getUserType());

        testUser.setUserId(createdUser.getUserId());
    }

    @Test
    @Order(2)
    void testLoginUser() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        User loginRequest = new User();
        loginRequest.setEmail(testUser.getEmail());
        loginRequest.setPassword(testUser.getPassword());

        HttpEntity<User> request = new HttpEntity<>(loginRequest, headers);

        ResponseEntity<User> response = restTemplate.postForEntity("/user/login", request, User.class);

        Assertions.assertEquals(HttpStatus.OK, response.getStatusCode());

        User loggedInUser = response.getBody();
        Assertions.assertNotNull(loggedInUser);
        Assertions.assertEquals(testUser.getEmail(), loggedInUser.getEmail());
        Assertions.assertEquals(testUser.getUserType(), loggedInUser.getUserType());
    }
}
