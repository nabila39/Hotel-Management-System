package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.RegisterUserDto;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public List<User> allUsers() {
        List<User> users = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    public RegisterUserDto updateUser(Integer id, Map<String, Object> userInfo) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userInfo.containsKey("email")) {
                user.setEmail((String) userInfo.get("email"));
            }
            if (userInfo.containsKey("fullName")) {
                user.setFullName((String) userInfo.get("fullName"));
            }

            User savedUser = userRepository.save(user);
            return RegisterUserDto.userToDto(savedUser);
        }
        return null;
    }
    public RegisterUserDto updateUserPassword(Integer id, Map<String, Object> userInfo) {
        Optional<User> userOptional = userRepository.findById(id);
        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (userInfo.containsKey("password")) {
                user.setPassword(passwordEncoder.encode((String) userInfo.get("password")));
            }
            User savedUser = userRepository.save(user);
            return RegisterUserDto.userToDto(savedUser);
        }
        return null;
    }
}
