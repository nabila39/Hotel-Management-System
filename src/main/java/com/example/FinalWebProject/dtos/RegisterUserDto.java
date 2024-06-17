package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.entities.User;
import lombok.Builder;

@Builder

public class RegisterUserDto {
    private String email;
    private String password;
    private String fullName;

    public String getEmail() {
        return email;
    }

    public RegisterUserDto setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public RegisterUserDto setPassword(String password) {
        this.password = password;
        return this;
    }

    public String getFullName() {
        return fullName;
    }

    public RegisterUserDto setFullName(String fullName) {
        this.fullName = fullName;
        return this;
    }
    public static RegisterUserDto userToDto(User user) {
        return RegisterUserDto.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .fullName(user.getFullName())
                .build();
    }
    @Override
    public String toString() {
        return "RegisterUserDto{" +
                "email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
