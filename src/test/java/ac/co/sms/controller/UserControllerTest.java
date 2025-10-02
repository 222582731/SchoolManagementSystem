package ac.co.sms.controller;

import ac.co.sms.domain.Lecturer;
import ac.co.sms.domain.Student;
import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserControllerTest {

    @Autowired
    private TestRestTemplate restTemplate;

    private static Student testStudent;
    private static Lecturer testLecturer;

    private final String baseUrl = "/user";

    @Test
    @Order(1)
    void registerStudent() {
        testStudent = new Student.Builder()
                .setName("Alice Van Wyk")
                .setEmail("222918302@mycput.ac.za")
                .setPassword("password123")
                .setUserType(UserType.STUDENT)
                .setStudentNumber("222918302")
                .setYearOfStudy(2)
                .build();

        ResponseEntity<Student> response = restTemplate.postForEntity(
                baseUrl + "/register", testStudent, Student.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getUserId());
        testStudent = response.getBody();
        System.out.println("Registered Student: " + testStudent);
    }

    @Test
    @Order(2)
    void registerLecturer() {
        testLecturer = new Lecturer.Builder()
                .setName("Dr. Shongwe")
                .setEmail("bob@cpu.ac.za")
                .setPassword("bob@cput.123")
                .setUserType(UserType.LECTURER)
                .setEmployeeNumber("EMP001")
                .setDepartment("Information Technology")
                .build();

        ResponseEntity<Lecturer> response = restTemplate.postForEntity(
                baseUrl + "/register", testLecturer, Lecturer.class
        );

        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertNotNull(response.getBody().getUserId());
        testLecturer = response.getBody();
        System.out.println("Registered Lecturer: " + testLecturer);
    }

    @Test
    @Order(3)
    void loginStudent() {
        User loginRequest = new User();
        loginRequest.setEmail("222918302@mycput.ac.za");
        loginRequest.setPassword("password123");

        ResponseEntity<User> response = restTemplate.postForEntity(
                baseUrl + "/login", loginRequest, User.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(testStudent.getUserId(), response.getBody().getUserId());
        System.out.println("Logged in Student: " + response.getBody());
    }

    @Test
    @Order(4)
    void readStudent() {
        ResponseEntity<Student> response = restTemplate.getForEntity(
                baseUrl + "/read/" + testStudent.getUserId(), Student.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals("Alice Van Wyk", response.getBody().getName());
        System.out.println("Read student: " + response.getBody());
    }

    @Test
    @Order(5)
    void updateStudent() {
        testStudent.setName("Candice");

        HttpEntity<Student> request = new HttpEntity<>(testStudent);
        ResponseEntity<Student> response = restTemplate.exchange(
                baseUrl + "/update/" + testStudent.getUserId(),
                HttpMethod.PUT,
                request,
                Student.class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Candice", response.getBody().getName());
        System.out.println("Updated student: " + response.getBody());
    }

    @Test
    @Order(6)
    void deleteLecturer() {
        restTemplate.delete(baseUrl + "/delete/" + testLecturer.getUserId());

        ResponseEntity<Lecturer> response = restTemplate.getForEntity(
                baseUrl + "/read/" + testLecturer.getUserId(), Lecturer.class
        );

        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        System.out.println("Deleted lecturer with ID: " + testLecturer.getUserId());
    }

    @Test
    @Order(7)
    void getAllUsers() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(baseUrl + "/all", Student[].class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<Student> users = List.of(response.getBody());
        assertTrue(users.size() >= 1);
        System.out.println("All users: " + users);
    }

    @Test
    @Order(8)
    void getUsersByType() {
        ResponseEntity<Student[]> response = restTemplate.getForEntity(
                baseUrl + "/type/STUDENT", Student[].class
        );

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        List<Student> students = List.of(response.getBody());
        assertTrue(students.stream().anyMatch(s -> "Candice".equals(s.getName())));
        System.out.println("Users with type STUDENT: " + students);
    }
}
