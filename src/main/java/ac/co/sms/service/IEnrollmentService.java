package ac.co.sms.service;

import ac.co.sms.domain.Enrollment;

import java.util.List;

public interface IEnrollmentService extends IService<Enrollment, Long>{

    List<Enrollment> findByStudent_StudentNumber(String studentNumber);

}
