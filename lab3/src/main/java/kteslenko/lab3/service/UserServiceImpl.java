package kteslenko.lab3.service;

import kteslenko.lab3.model.Authority;
import kteslenko.lab3.model.User;
import kteslenko.lab3.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@AllArgsConstructor
@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;
    PasswordEncoder passwordEncoder;

    public UserDetails loadUserByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow();
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).orElseThrow(
                () -> new NoSuchElementException("User with id %d was not found".formatted(id))
        );
    }

    public List<User> getAllUsersExcept(User user) {
        return userRepository.findAllByIdNot(user.getId());
    }

    public void insertUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setAuthorities(List.of(new Authority("ROLE_USER")));

        throwIfExists(user);
        userRepository.save(user);
    }

    public void updateUser(Long id, User user) {
        User userFromDb = getUserById(id);

        userFromDb.setUsername(user.getUsername());
        if (!user.getPassword().isBlank()) {
            String encodedPassword = passwordEncoder.encode(user.getPassword());
            userFromDb.setPassword(encodedPassword);
        }

        throwIfExists(userFromDb);
        userRepository.save(userFromDb);
    }

    private void throwIfExists(User user) {
        userRepository.findByUsername(user.getUsername())
                .filter(existing -> !existing.getId().equals(user.getId()))
                .ifPresent(existing -> {
                    throw new DataIntegrityViolationException("User with username %s already exists"
                            .formatted(user.getUsername()));
                });
    }

    public void deleteUserById(Long id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
}
