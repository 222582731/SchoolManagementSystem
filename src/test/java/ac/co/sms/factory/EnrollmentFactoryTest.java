package ac.co.sms.factory;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.domain.Student;
import ac.co.sms.factory.EnrollmentFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class EnrollmentFactoryTest {

    @Test
    void createEnrollment() {
        Student student = new Student.Builder()
                .setName("Yanga Jilaji")
                .setEmail("222391749@mycput.ac.za")
                .setPassword("YangaJil@2025")
                .setUserType(ac.co.sms.domain.enums.UserType.STUDENT)
                .setStudentNumber("ST12345")
                .setYearOfStudy(3)
                .build();

        Enrollment enrollment = EnrollmentFactory.createEnrollment(
                student,
                "CS101",
                "Introduction to Programming"
        );

        assertNotNull(enrollment);
        assertNotNull(enrollment.getStudent());
        assertEquals("CS101", enrollment.getCourseCode());
        assertEquals("Introduction to Programming", enrollment.getCourseName());
        System.out.println("ENROLLMENT: " + enrollment);
    }
}
