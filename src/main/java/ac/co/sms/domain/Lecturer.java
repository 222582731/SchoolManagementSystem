package ac.co.sms.domain;

import ac.co.sms.domain.enums.UserType;
import jakarta.persistence.Entity;

@Entity
public class Lecturer extends User {

    private String employeeNumber;
    private String department;

    public Lecturer() {}

    public Lecturer(Builder builder){
        super(builder.name, builder.email, builder.password, builder.userType); // call User constructor
        this.employeeNumber = builder.employeeNumber;
        this.department = builder.department;
    }

    public String getEmployeeNumber() { return employeeNumber; }
    public String getDepartment() { return department; }

    @Override
    public String toString() {
        return super.toString() +
                ", Lecturer{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public static class Builder {
        private String name;
        private String email;
        private String password;
        private UserType userType;

        private String employeeNumber;
        private String department;

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

        public Builder setEmployeeNumber(String employeeNumber){
            this.employeeNumber = employeeNumber;
            return this;
        }
        public Builder setDepartment(String department){
            this.department = department;
            return this;
        }

        public Builder copy(Lecturer lecturer){
            this.name = lecturer.getName();
            this.email = lecturer.getEmail();
            this.password = lecturer.getPassword();
            this.userType = lecturer.getUserType();
            this.employeeNumber = lecturer.getEmployeeNumber();
            this.department = lecturer.getDepartment();
            return this;
        }

        public Lecturer build(){
            return new Lecturer(this);
        }
    }
}
