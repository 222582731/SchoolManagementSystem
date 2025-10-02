package ac.co.sms.domain;

import ac.co.sms.domain.enums.UserType;
import jakarta.persistence.*;

@Entity
@Table(name = "users")
@Inheritance(strategy = InheritanceType.JOINED)
public class User {
    @Id
    @GeneratedValue
    private Long userId;
    private String name;
    private String email;
    private String password;

    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User() {}

    public User(Builder builder){
        this.userId = builder.userId;
        this.name = builder.name;
        this.email = builder.email;
        this.password = builder.password;
        this.userType = builder.userType;
    }

    public User(String name, String email, String password, UserType userType) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.userType = userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public UserType getUserType() {
        return userType;
    }


    //used in my serviceTest & controllerTest
    public void setUserId(Long userId) {
        this.userId = userId;
    }
    public void setEmail(String mail) {
        this.email = mail;
    }

    public void setPassword(String password123) {
        this.password = password123;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", userType=" + userType +
                '}';
    }

    public static class Builder{
        private Long userId;
        private String name;
        private String email;
        private String password;
        private UserType userType;

        public Builder setUserId(Long userId){
            this.userId = userId;
            return this;
        }
        public Builder setName(String name){
            this.name = name;
            return this;
        }
        public Builder setEmail(String email){
            this.email = email;
            return this;
        }
        public Builder setPassword(String password){
            this.password = password;
            return this;
        }
        public Builder setUserType(UserType userType){
            this.userType = userType;
            return this;
        }

        public Builder copy(User user){
            this.userId = user.getUserId();
            this.name = user.getName();
            this.email = user.getEmail();
            this.password = user.getPassword();
            this.userType = user.getUserType();
            return this;
        }

        public User build(){
            return new User(this);
        }

    }
}
