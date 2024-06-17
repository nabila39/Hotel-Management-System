package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.UserRepository;
import com.example.FinalWebProject.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;
@Service
public class employeeService {
    @Autowired
    private employeeRepository employeeRepository;


    public EmployeeDto addEmployee(@RequestBody EmployeeDto employeeDto) {
        return EmployeeDto.ToDto(employeeRepository.save(Employee.toEntity(employeeDto)));
    }
    public List<Employee> allEmployee() {
        List<Employee> employees = new ArrayList<>();
        employeeRepository.findAll().forEach(employees::add);
        return employees;
    }

}
