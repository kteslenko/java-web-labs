package kteslenko.lab3.service;

import kteslenko.lab3.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;

public interface UserService extends UserDetailsService {
    User getUserById(Long username);

    List<User> getAllUsersExcept(User user);

    void insertUser(User user);

    void updateUser(Long id, User user);

    void deleteUserById(Long id);
}
