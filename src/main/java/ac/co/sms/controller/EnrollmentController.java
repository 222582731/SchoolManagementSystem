package ac.co.sms.controller;

import ac.co.sms.domain.Enrollment;
import ac.co.sms.service.EnrollmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/enrollment")
@CrossOrigin(origins = "*")
public class EnrollmentController {

    private final EnrollmentService enrollmentService;

    public EnrollmentController(EnrollmentService enrollmentService) {
        this.enrollmentService = enrollmentService;
    }

    @PostMapping(produces = "application/json", consumes = "application/json")
    public ResponseEntity<Enrollment> createEnrollment(@RequestBody Enrollment enrollment) {
        Enrollment saved = enrollmentService.save(enrollment);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    @GetMapping(produces = "application/json")
    public ResponseEntity<List<Enrollment>> getAllEnrollments() {
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @GetMapping(value = "/{enrollmentId}", produces = "application/json")
    public ResponseEntity<Enrollment> getEnrollmentById(@PathVariable Long enrollmentId) {
        Enrollment enrollment = enrollmentService.read(enrollmentId);
        return enrollment != null ? ResponseEntity.ok(enrollment) : ResponseEntity.notFound().build();
    }

    @PutMapping(value = "/{enrollmentId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Enrollment> updateEnrollment(
            @PathVariable Long enrollmentId,
            @RequestBody Enrollment enrollment
    ) {
        enrollment.setEnrollmentId(enrollmentId);
        try {
            Enrollment updated = enrollmentService.update(enrollment);
            return ResponseEntity.ok(updated);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PatchMapping(value = "/{enrollmentId}", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Enrollment> updateEnrollmentPartial(
            @PathVariable Long enrollmentId,
            @RequestBody Map<String, Object> updates
    ) {
        Enrollment existing = enrollmentService.read(enrollmentId);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }

        if (updates.containsKey("grade")) {
            existing.setGrade((String) updates.get("grade"));
        }

        if (updates.containsKey("attendance")) {
            existing.setAttendance((String) updates.get("attendance"));
        }

        Enrollment updated = enrollmentService.update(existing);
        return ResponseEntity.ok(updated);
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

    @GetMapping(value = "/student/{studentNumber}", produces = "application/json")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByStudentNumber(@PathVariable String studentNumber) {
        List<Enrollment> enrollments = enrollmentService.findByStudent_StudentNumber(studentNumber);
        return ResponseEntity.ok(enrollments);
    }

    @GetMapping(value = "/course/{courseCode}", produces = "application/json")
    public ResponseEntity<List<Enrollment>> getEnrollmentsByCourse(@PathVariable String courseCode) {
        List<Enrollment> enrollments = enrollmentService.findByCourseCode(courseCode);
        return ResponseEntity.ok(enrollments);
    }
}
