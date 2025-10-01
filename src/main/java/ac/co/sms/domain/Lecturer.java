package ac.co.sms.domain;

import jakarta.persistence.Entity;

@Entity
public class Lecturer extends User {

    private String employeeNumber;
    private String department;

    public Lecturer() {}

    public Lecturer(Builder builder){
        super(builder); // call User's builder
        this.employeeNumber = builder.employeeNumber;
        this.department = builder.department;
    }

    public String getEmployeeNumber() {
        return employeeNumber;
    }

    public String getDepartment() {
        return department;
    }

    @Override
    public String toString() {
        return super.toString() +
                ", Lecturer{" +
                "employeeNumber='" + employeeNumber + '\'' +
                ", department='" + department + '\'' +
                '}';
    }

    public static class Builder extends User.Builder {
        private String employeeNumber;
        private String department;

        public Builder setEmployeeNumber(String employeeNumber){
            this.employeeNumber = employeeNumber;
            return this;
        }

        public Builder setDepartment(String department){
            this.department = department;
            return this;
        }

        public Builder copy(Lecturer lecturer){
            super.copy(lecturer);
            this.employeeNumber = lecturer.getEmployeeNumber();
            this.department = lecturer.getDepartment();
            return this;
        }

        @Override
        public Lecturer build(){
            return new Lecturer(this);
        }
    }
}
