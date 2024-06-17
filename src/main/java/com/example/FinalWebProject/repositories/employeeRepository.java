package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeRepository extends JpaRepository<Employee, Integer> {
}
