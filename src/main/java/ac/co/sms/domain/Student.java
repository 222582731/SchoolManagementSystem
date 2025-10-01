package ac.co.sms.domain;

import ac.co.sms.domain.enums.UserType;
import jakarta.persistence.Entity;

@Entity
public class Student extends User {

    private String studentNumber;
    private Integer yearOfStudy;

    public Student() {}

    public Student(Builder builder){
        super(builder.name, builder.email, builder.password, builder.userType); // call User constructor
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



    public static class Builder {
        private String name;
        private String email;
        private String password;
        private UserType userType;

        private String studentNumber;
        private Integer yearOfStudy;

        public Builder setName(String name){
            this.name = name; return this;
        }
        public Builder setEmail(String email){
            this.email = email; return this;
        }
        public Builder setPassword(String password){
            this.password = password; return this;
        }
        public Builder setUserType(UserType userType){
            this.userType = userType; return this;
        }
        public Builder setStudentNumber(String studentNumber){
            this.studentNumber = studentNumber; return this;
        }
        public Builder setYearOfStudy(Integer yearOfStudy){
            this.yearOfStudy = yearOfStudy; return this;
        }

        public Builder copy(Student student){
            this.name = student.getName();
            this.email = student.getEmail();
            this.password = student.getPassword();
            this.userType = student.getUserType();
            this.studentNumber = student.getStudentNumber();
            this.yearOfStudy = student.getYearOfStudy();
            return this;
        }

        public Student build(){
            return new Student(this);
        }
    }
}
