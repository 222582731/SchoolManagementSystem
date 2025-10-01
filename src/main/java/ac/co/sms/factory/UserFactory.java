package ac.co.sms.factory;

import ac.co.sms.domain.*;
import ac.co.sms.domain.enums.UserType;

public class UserFactory {

    public static User createUser(UserType userType, String name, String email, String password,
                                  String identifier, String extra) {
        switch (userType) {
            case STUDENT:
                return new Student.Builder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserType(UserType.STUDENT)
                        .setStudentNumber(identifier)
                        .setYearOfStudy(Integer.parseInt(extra))
                        .build();
            case LECTURER:
                return new Lecturer.Builder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserType(UserType.LECTURER)
                        .setEmployeeNumber(identifier)
                        .setDepartment(extra)
                        .build();
            case ADMIN:
                return new User.Builder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .setUserType(UserType.ADMIN)
                        .build();
            default:
                throw new IllegalArgumentException("Invalid user type");
        }
    }
}
