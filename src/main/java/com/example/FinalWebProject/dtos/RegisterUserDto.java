package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.User;
import org.springframework.hateoas.RepresentationModel;

public class RegisterUserDto extends RepresentationModel<RegisterUserDto> {
    private String email;
    private String password;
    private String fullName;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    // Add a static method to create a DTO from a User entity
    public static RegisterUserDto userToDto(User user) {
        RegisterUserDto dto = new RegisterUserDto();
        dto.setEmail(user.getEmail());
        dto.setPassword(user.getPassword());
        dto.setFullName(user.getFullName());
        return dto;
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
