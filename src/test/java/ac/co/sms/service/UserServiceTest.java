package ac.co.sms.service;

import ac.co.sms.domain.Lecturer;
import ac.co.sms.domain.Student;
import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class UserServiceTest {

    @Autowired
    private UserService userService;

    private static Student student;
    private static Lecturer lecturer;

    @Test
    void a_saveUsers() {
        //create Student
        student = new Student.Builder()
                .setName("Alice Van Wyk")
                .setEmail("222918302@mycput.ac.za")
                .setPassword("password123")
                .setUserType(UserType.STUDENT)
                .setStudentNumber("222918302")
                .setYearOfStudy(2)
                .build();

        //create Lecturer
        lecturer = new Lecturer.Builder()
                .setName("Dr. Shongwe")
                .setEmail("bob@cpu.ac.za")
                .setPassword("bob@cput.123")
                .setUserType(UserType.LECTURER)
                .setEmployeeNumber("EMP001")
                .setDepartment("Information Technology")
                .build();

        Student savedStudent = (Student) userService.save(student);
        Lecturer savedLecturer = (Lecturer) userService.save(lecturer);

        student = savedStudent;
        lecturer = savedLecturer;

        assertNotNull(savedStudent.getUserId());
        assertNotNull(savedLecturer.getUserId());

        System.out.println("Saved Student: " + savedStudent);
        System.out.println("Saved Lecturer: " + savedLecturer);
    }

    @Test
    void b_findAllUsers() {
        List<User> users = userService.findAll();
        assertTrue(users.size() >= 2);

        System.out.println("All Users: " + users);
    }

    @Test
    void c_findByUserType() {
        List<User> students = userService.findByUserType(UserType.STUDENT);
        List<User> lecturers = userService.findByUserType(UserType.LECTURER);

        assertFalse(students.isEmpty(), "Students should not be empty");
        assertFalse(lecturers.isEmpty(), "Lecturers should not be empty");

        System.out.println("Students: " + students);
        System.out.println("Lecturers: " + lecturers);
    }

    @Test
    void d_updateStudent() {
        User fetched = userService.read(student.getUserId());
        assertNotNull(fetched);

        fetched.setName("Candice");
        User updated = userService.update(fetched);

        assertEquals("Candice", updated.getName());

        User reloaded = userService.read(student.getUserId());
        assertEquals("Candice", reloaded.getName());
        System.out.println("Updated Student: " + reloaded);
    }

    @Test
    void e_deleteLecturer() {
        userService.deleteById(lecturer.getUserId());
        User deleted = userService.read(lecturer.getUserId());

        assertNull(deleted);

        System.out.println("Deleted Lecturer ID: " + lecturer.getUserId());
    }
}
