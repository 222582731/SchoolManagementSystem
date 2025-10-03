package ac.co.sms.service;

import ac.co.sms.domain.Enrollment;
import com.jayway.jsonpath.JsonPath;

import java.util.List;

public interface IEnrollmentService extends IService<Enrollment, Long>{

    List<Enrollment> findByStudent_StudentNumber(String studentNumber);
    List<Enrollment> findByCourseCode(String courseCode);
   //Enrollment findById(long id);


    //JsonPath findById(Long id);
}
