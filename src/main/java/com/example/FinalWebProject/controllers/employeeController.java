package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.BillsDto;
import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/employee")


public class employeeController {
    private final EmployeeService employeeService;

    @Autowired
    public employeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }


    @PostMapping("/addEmployee")

    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto employeeDto) {
        try {
            EmployeeDto savedEmployee = employeeService.addEmployee(employeeDto);
            return ResponseEntity.ok(savedEmployee);
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }

    @GetMapping("/allEmployee")

    public ResponseEntity<List<EmployeeDto>> allEmployee() {
        List<EmployeeDto> employees = employeeService.allEmployee();
        return ResponseEntity.ok(employees);
    }


    @PutMapping("/update")
    public EmployeeDto updateEmployee(@RequestParam Integer id, @RequestBody EmployeeDto employeeDto) throws Exception {
        return employeeService.updateEmployee(id, employeeDto);
    }
    @DeleteMapping("/delete")
    public ResponseEntity<?> deleteEmployee(@RequestParam Integer id) {
        try {
            employeeService.deleteEmployee(id);
            return ResponseEntity.ok("Employee deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }
}
