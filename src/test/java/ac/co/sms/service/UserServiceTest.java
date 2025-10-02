package ac.co.sms.service;

import ac.co.sms.domain.Lecturer;
import ac.co.sms.domain.Student;
import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private static Student student;
    private static Lecturer lecturer;

    @Test
    void a_registerUsers() {
        // Register Student
        student = new Student.Builder()
                .setName("Alice Van Wyk")
                .setEmail("22334@mycput.ac.za")
                .setPassword("password123")
                .setUserType(UserType.STUDENT)
                .setStudentNumber("22334")
                .setYearOfStudy(2)
                .build();

        // Register Lecturer
        lecturer = new Lecturer.Builder()
                .setName("Dr. Shongwe")
                .setEmail("bule@cpu.ac.za")
                .setPassword("bulelani.b@cput.123")
                .setUserType(UserType.LECTURER)
                .setEmployeeNumber("EMP001")
                .setDepartment("Information Technology")
                .build();

        Student savedStudent = (Student) userService.register(student);
        Lecturer savedLecturer = (Lecturer) userService.register(lecturer);

        student = savedStudent;
        lecturer = savedLecturer;

        assertNotNull(savedStudent.getUserId());
        assertNotNull(savedLecturer.getUserId());

        System.out.println("Registered Student: " + savedStudent);
        System.out.println("Registered Lecturer: " + savedLecturer);
    }

    @Test
    void b_loginStudent() {
        Optional<User> loggedIn = userService.login("22334@mycput.ac.za", "password123");
        assertTrue(loggedIn.isPresent());
        assertEquals(student.getUserId(), loggedIn.get().getUserId());
        System.out.println("Logged in Student: " + loggedIn.get());
    }

    @Test
    void c_findAllUsers() {
        List<User> users = userService.findAll();
        assertTrue(users.size() >= 2);
        System.out.println("All Users: " + users);
    }

    @Test
    void d_findByUserType() {
        List<User> students = userService.findByUserType(UserType.STUDENT);
        List<User> lecturers = userService.findByUserType(UserType.LECTURER);

        assertFalse(students.isEmpty(), "Students should not be empty");
        assertFalse(lecturers.isEmpty(), "Lecturers should not be empty");

        System.out.println("Students: " + students);
        System.out.println("Lecturers: " + lecturers);
    }

    @Test
    void e_updateStudent() {
        User fetched = userService.read(student.getUserId());
        assertNotNull(fetched);

        fetched.setName("Candice");
        User updated = userService.update(fetched);

        assertEquals("Candice", updated.getName());
        System.out.println("Updated Student: " + updated);
    }

    @Test
    void f_deleteLecturer() {
        userService.deleteById(lecturer.getUserId());
        User deleted = userService.read(lecturer.getUserId());

        assertNull(deleted);
        System.out.println("Deleted Lecturer ID: " + lecturer.getUserId());
    }
}
