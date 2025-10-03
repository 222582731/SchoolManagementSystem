//package ac.co.sms.controller;
//
//import ac.co.sms.domain.Enrollment;
//import ac.co.sms.domain.Student;
//import ac.co.sms.domain.enums.EnrollmentStatus;
//import ac.co.sms.repository.EnrollmentRepository;
//import ac.co.sms.repository.UserRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.client.TestRestTemplate;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.http.*;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class EnrollmentControllerTest {
//
//    @LocalServerPort
//    private int port;
//
//    @Autowired
//    private TestRestTemplate restTemplate;
//
//    @Autowired
//    private EnrollmentRepository enrollmentRepository;
//
//    @Autowired
//    private UserRepository userRepository;
//
//    private Student student;
//
//    private String baseUrl;
//
//    @BeforeEach
//    void setUp() {
//        enrollmentRepository.deleteAll();
//        userRepository.deleteAll();
//
//        student = new Student();
//        student.setStudentNumber("S123");
//        student.setName("John Doe");
//        student.setEmail("john@example.com");
//        userRepository.save(student);
//
//        baseUrl = "http://localhost:" + port + "/enrollment";
//    }
//
//    @Test
//    void testCreateAndGetEnrollment() {
//        Enrollment enrollment = new Enrollment.Builder()
//                .setStudent(student)
//                .setCourseCode("CS101")
//                .setCourseName("Intro to CS")
//                .setEnrollmentDate(LocalDate.now())
//                .setStatus(EnrollmentStatus.ENROLLED)
//                .build();
//
//        // Create enrollment
//        ResponseEntity<Enrollment> response = restTemplate.postForEntity(
//                baseUrl, enrollment, Enrollment.class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
//        assertThat(response.getBody()).isNotNull();
//        assertThat(response.getBody().getCourseCode()).isEqualTo("CS101");
//
//        Long enrollmentId = response.getBody().getEnrollmentId();
//
//        // Get enrollment by id
//        ResponseEntity<Enrollment> getResponse = restTemplate.getForEntity(
//                baseUrl + "/" + enrollmentId, Enrollment.class);
//
//        assertThat(getResponse.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(getResponse.getBody()).isNotNull();
//        assertThat(getResponse.getBody().getCourseName()).isEqualTo("Intro to CS");
//    }
//    @Test
//    void testGetAllEnrollments() {
//        Enrollment e1 = new Enrollment.Builder()
//                .setStudent(student)
//                .setCourseCode("CS101")
//                .setCourseName("Intro to CS")
//                .setEnrollmentDate(LocalDate.now())
//                .setStatus(EnrollmentStatus.ENROLLED)
//                .build();
//
//        Enrollment e2 = new Enrollment.Builder()
//                .setStudent(student)
//                .setCourseCode("MATH101")
//                .setCourseName("Mathematics")
//                .setEnrollmentDate(LocalDate.now())
//                .setStatus(EnrollmentStatus.ENROLLED)
//                .build();
//
//        enrollmentRepository.saveAll(List.of(e1, e2));
//
//        ResponseEntity<Enrollment[]> response =
//                restTemplate.getForEntity(baseUrl, Enrollment[].class);
//
//        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
//        assertThat(response.getBody()).hasSize(2);
//    }
//
//    @Test
//    void testDeleteEnrollment() {
//        Enrollment e1 = new Enrollment.Builder()
//                .setStudent(student)
//                .setCourseCode("CS101")
//                .setCourseName("Intro to CS")
//                .setEnrollmentDate(LocalDate.now())
//                .setStatus(EnrollmentStatus.ENROLLED)
//                .build();
//
//        Enrollment saved = enrollmentRepository.save(e1);
//
//        restTemplate.delete(baseUrl + "/" + saved.getEnrollmentId());
//
//        assertThat(enrollmentRepository.findById(saved.getEnrollmentId())).isEmpty();
//    }
//}
