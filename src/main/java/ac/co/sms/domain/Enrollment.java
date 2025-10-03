package ac.co.sms.domain;

import ac.co.sms.domain.enums.EnrollmentStatus;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class Enrollment {

    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    private String courseCode;
    private String courseName;
    private LocalDate enrollmentDate;

    @Enumerated(EnumType.STRING)
    private EnrollmentStatus status;

    public Enrollment() {}

    public Enrollment(Builder builder){
        this.id = builder.id;
        this.student = builder.student;
        this.courseCode = builder.courseCode;
        this.courseName = builder.courseName;
        this.enrollmentDate = builder.enrollmentDate;
        this.status = builder.status;
    }

    public Long getId() {
        return id;
    }
    public Student getStudent() {
        return student;
    }
    public String getCourseCode() {
        return courseCode;
    }
    public String getCourseName() {
        return courseName;
    }
    public LocalDate getEnrollmentDate() {
        return enrollmentDate;
    }
    public EnrollmentStatus getStatus() {
        return status;
    }

    //will be called in my service
    public Long getEnrollmentId() {
        return id;
    }

    //used in the serviceTest
    public void setEnrollmentDate(LocalDate of) {
        this.enrollmentDate = of;
    }
    public void setEnrollmentId(Long enrollmentId) {
        this.id = enrollmentId;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "id=" + id +
                ", student=" + student +
                ", courseCode='" + courseCode + '\'' +
                ", courseName='" + courseName + '\'' +
                ", enrollmentDate=" + enrollmentDate +
                ", status=" + status +
                '}';
    }

    public void setAttendance(String attendance) {
        
    }

    public void setGrade(String grade) {
    }


    public static class Builder{
        private Long id;
        private Student student;
        private String courseCode;
        private String courseName;
        private LocalDate enrollmentDate;
        private EnrollmentStatus status;

        public Builder setId(Long id){
            this.id = id;
            return this;
        }
        public Builder setStudent(Student student){
            this.student = student;
            return this;
        }
        public Builder setCourseCode(String courseCode){
            this.courseCode = courseCode;
            return this;
        }
        public Builder setCourseName(String courseName){
            this.courseName = courseName;
            return this;
        }
        public Builder setEnrollmentDate(LocalDate enrollmentDate){
            this.enrollmentDate = enrollmentDate;
            return this;
        }
        public Builder setStatus(EnrollmentStatus status){
            this.status = status;
            return this;
        }

        public Builder copy(Enrollment enrollment){
            this.id = enrollment.getId();
            this.student = enrollment.getStudent();
            this.courseCode = enrollment.getCourseCode();
            this.courseName = enrollment.getCourseName();
            this.enrollmentDate = enrollment.getEnrollmentDate();
            this.status = enrollment.getStatus();
            return this;
        }

        public Enrollment build(){
            return new Enrollment(this);
        }
    }
}
