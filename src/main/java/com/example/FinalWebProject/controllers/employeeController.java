package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.dtos.EmployeeTasksDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.services.RoomService;
import com.example.FinalWebProject.services.employeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")
public class employeeController {
    private final employeeService employeeService;

    @Autowired
    public employeeController(employeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/addEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<EmployeeDto> addEmployee(@RequestBody EmployeeDto employeeDto) {
        EmployeeDto addedemployee = employeeService.addEmployee(employeeDto);
        return new ResponseEntity<>(addedemployee, HttpStatus.CREATED);
    }

    @GetMapping("/allEmployee")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<Employee>> allEmployee() {
        List<Employee> employees = employeeService.allEmployee();
        return ResponseEntity.ok(employees);
    }
}
