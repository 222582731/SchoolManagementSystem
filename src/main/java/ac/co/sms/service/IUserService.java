package ac.co.sms.service;

import ac.co.sms.domain.User;
import ac.co.sms.domain.enums.UserType;

import java.util.*;

public interface IUserService extends IService<User, Long>{

List<User> findByUserType(UserType userType);
//Optional<User> findAll(User  user);
}
