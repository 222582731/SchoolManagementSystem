package ac.co.sms.service;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.repository.EnrollmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EnrollmentService implements IEnrollmentService {

    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public Enrollment save(Enrollment entity) {
        return enrollmentRepository.save(entity);
    }

    @Override
    public Enrollment read(Long enrollmentId) {
        return enrollmentRepository.findById(enrollmentId)
                .orElse(null);
    }

    @Override
    public Enrollment update(Enrollment entity) {
        if (enrollmentRepository.existsById(entity.getEnrollmentId())) {
            return enrollmentRepository.save(entity); // save updates if ID exists
        }
        throw new IllegalArgumentException("Enrollment not found with id: " + entity.getEnrollmentId());
    }

    @Override
    public List<Enrollment> findByStudent_StudentNumber(String studentNumber) {
        return enrollmentRepository.findByStudent_StudentNumber(studentNumber);
    }

    @Override
    public List<Enrollment> findAll() {
        return enrollmentRepository.findAll();
    }

    @Override
    public void deleteById(Long enrollmentId) {
        if (enrollmentRepository.existsById(enrollmentId)) {
            enrollmentRepository.deleteById(enrollmentId);
        } else {
            throw new IllegalArgumentException("Enrollment with ID " + enrollmentId + " not found");
        }
    }
}
