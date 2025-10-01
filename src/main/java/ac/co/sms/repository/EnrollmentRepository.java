package ac.co.sms.repository;

import ac.co.sms.domain.Enrollment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {

    List<Enrollment> findByStudentNumber(String studentNumber);
    List<Enrollment> findByCourseCode(String courseCode);
    Enrollment findByStudentNumberAndCourseCode(String studentNumber, String courseCode);
}
