package ac.co.sms.controller;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/enrollment")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.save(enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {
        Enrollment enrollment = enrollmentService.read(enrollmentId);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{enrollmentId}")
    public ResponseEntity<Enrollment> updateEnrollment(@PathVariable Long enrollmentId, @RequestBody Enrollment enrollment) {
        enrollment.setEnrollmentId(enrollmentId); // ensure the ID is set
        try {
            Enrollment updated = enrollmentService.update(enrollment);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{enrollmentId}")
    public ResponseEntity<Void> deleteEnrollment(@PathVariable Long enrollmentId) {
        try {
            enrollmentService.deleteById(enrollmentId);
            return ResponseEntity.noContent().build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/student/{studentNumber}")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentNumber(@PathVariable String studentNumber) {
        List<Enrollment> enrollments = enrollmentService.findByStudent_StudentNumber(studentNumber);
        return ResponseEntity.ok(enrollments);
    }
}
