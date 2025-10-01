package ac.co.sms.factory;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;
import ac.co.sms.factory.UserFactory;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class UserFactoryTest {

    @Test
    void createUserAsStudent() {
        User student = UserFactory.createUser(
                UserType.STUDENT,
                "Yanga Jilaji",
                "222391749@mycput.ac.za",
                "YangaJil@2025",
                "ST12345",
                "3"
        );
        assertNotNull(student);
        assertEquals(UserType.STUDENT, student.getUserType());
        System.out.println("STUDENT: " + student);
    }

    @Test
    void createUserAsLecturer() {
        User lecturer = UserFactory.createUser(
                UserType.LECTURER,
                "Dr. Smith",
                "smith@cput.ac.za",
                "Password@123",
                "EMP789",
                "Information Technology"
        );
        assertNotNull(lecturer);
        assertEquals(UserType.LECTURER, lecturer.getUserType());
        System.out.println("LECTURER: " + lecturer);
    }

    @Test
    void createUserAsAdmin() {
        User admin = UserFactory.createUser(
                UserType.ADMIN,
                "DR. Doe",
                "doe.isaac@cput.ac.za",
                "Password@2025",
                null,
                null
        );
        assertNotNull(admin);
        assertEquals(UserType.ADMIN, admin.getUserType());
        System.out.println("ADMIN: " + admin);
    }
}
