package ac.co.sms.factory;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.domain.Student;
import ac.co.sms.domain.enums.EnrollmentStatus;
import ac.co.sms.util.Helper;

import java.time.LocalDate;

public class EnrollmentFactory {

    public static Enrollment createEnrollment(Student student, String courseCode, String courseName) {
        if (student == null || Helper.isNullOrEmpty(courseCode) || Helper.isNullOrEmpty(courseName)) {
            throw new IllegalArgumentException("Invalid arguments for creating Enrollment");
        }

        return new Enrollment.Builder()
                .setStudent(student)
                .setCourseCode(courseCode)
                .setCourseName(courseName)
                .setEnrollmentDate(LocalDate.now())
                .setStatus(EnrollmentStatus.ENROLLED)
                .build();
    }
}
