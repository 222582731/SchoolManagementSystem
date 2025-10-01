package ac.co.sms.service;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.domain.Student;
import ac.co.sms.domain.enums.UserType;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@TestMethodOrder(MethodOrderer.MethodName.class)
public class EnrollmentServiceTest {

    @Autowired
    private EnrollmentService enrollmentService;

    @Autowired
    private UserService userService;

    private static Student student;
    private static Enrollment enrollment;

    @Test
    void a_setupStudent() {
        // Create student
        student = new Student.Builder()
                .setName("Alice Van Wyk")
                .setEmail("alice@student.com")
                .setPassword("pass123")
                .setUserType(UserType.STUDENT)
                .setStudentNumber("S12345")
                .setYearOfStudy(2)
                .build();
        student = (Student) userService.save(student);

        System.out.println("Setup Student: " + student);
    }

    @Test
    void b_saveEnrollment() {
        enrollment = new Enrollment.Builder()
                .setStudent(student)
                .setEnrollmentDate(LocalDate.now())
                .build();

        Enrollment saved = enrollmentService.save(enrollment);
        assertNotNull(saved.getEnrollmentId());
        enrollment = saved;

        System.out.println("Saved Enrollment: " + saved);
    }

    @Test
    void c_findAllEnrollments() {
        List<Enrollment> enrollments = enrollmentService.findAll();
        assertFalse(enrollments.isEmpty());
        System.out.println("All Enrollments: " + enrollments);
    }

    @Test
    void d_findByStudentNumber() {
        List<Enrollment> studentEnrollments = enrollmentService.findByStudent_StudentNumber(student.getStudentNumber());
        assertFalse(studentEnrollments.isEmpty());
        assertEquals(student.getStudentNumber(), studentEnrollments.get(0).getStudent().getStudentNumber());
        System.out.println("Enrollments for student: " + studentEnrollments);
    }

    @Test
    void e_updateEnrollment() {
        enrollment.setEnrollmentDate(LocalDate.of(2025, 10, 1));
        Enrollment updated = enrollmentService.update(enrollment);

        assertEquals(LocalDate.of(2025, 10, 1), updated.getEnrollmentDate());

        Enrollment reloaded = enrollmentService.read(enrollment.getEnrollmentId());
        assertEquals(LocalDate.of(2025, 10, 1), reloaded.getEnrollmentDate());

        System.out.println("Updated Enrollment: " + updated);
    }

    @Test
    void f_deleteEnrollment() {
        enrollmentService.deleteById(enrollment.getEnrollmentId());
        Enrollment deleted = enrollmentService.read(enrollment.getEnrollmentId());
        assertNull(deleted);
        System.out.println("Deleted Enrollment ID: " + enrollment.getEnrollmentId());
    }
}
