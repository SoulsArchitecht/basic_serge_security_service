package ru.sshibko.bsss.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.sshibko.bsss.entity.Role;
import ru.sshibko.bsss.entity.User;
import ru.sshibko.bsss.exception.UserNotFoundException;
import ru.sshibko.bsss.repository.UserRepository;

import java.util.Collections;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;

    public User createNewAccount(User user, Role role) {
        user.setRoles(Collections.singletonList(role));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        role.setUser(user);

        return userRepository.saveAndFlush(user);
    }

    public User findByUserName(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(
                        "User with username " + username + " is not exist"));
    }
}
