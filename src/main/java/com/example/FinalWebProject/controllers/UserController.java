package com.example.FinalWebProject.controllers;


import com.example.FinalWebProject.dtos.RegisterUserDto;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RequestMapping("/users")
@RestController
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/view")
    public ResponseEntity<User> authenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        return ResponseEntity.ok(currentUser);
    }

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<User>> allUsers() {
        List<User> users = userService.allUsers();
        return ResponseEntity.ok(users);
    }
    @PatchMapping("/update")
    public RegisterUserDto UpdateUser(@RequestParam Integer id, @RequestBody Map<String, Object> User){
        return userService.updateUser(id, User);
    }
    @PatchMapping("/updateUserPassword")
    public ResponseEntity<String> updateUserPassword(@RequestParam Integer id, @RequestBody Map<String, Object> user) {
        userService.updateUserPassword(id, user);
        return ResponseEntity.ok("Password updated successfully");
    }

}
