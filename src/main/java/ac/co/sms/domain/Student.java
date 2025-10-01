package ac.co.sms.domain;

import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    private String studentNumber;
    private Integer yearOfStudy;

    public Student() {}

    public Student(Builder builder){
        super(builder); // call User's builder
        this.studentNumber = builder.studentNumber;
        this.yearOfStudy = builder.yearOfStudy;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public Integer getYearOfStudy() {
        return yearOfStudy;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Student{" +
                "studentNumber='" + studentNumber + '\'' +
                ", yearOfStudy=" + yearOfStudy +
                '}';
    }

    public static class Builder extends User.Builder {
        private String studentNumber;
        private Integer yearOfStudy;

        public Builder setStudentNumber(String studentNumber){
            this.studentNumber = studentNumber;
            return this;
        }

        public Builder setYearOfStudy(Integer yearOfStudy){
            this.yearOfStudy = yearOfStudy;
            return this;
        }

        public Builder copy(Student student){
            super.copy(student);
            this.studentNumber = student.getStudentNumber();
            this.yearOfStudy = student.getYearOfStudy();
            return this;
        }

        @Override
        public Student build(){
            return new Student(this);
        }
    }
}
