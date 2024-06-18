package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.EmployeeTasks;
import org.springframework.data.jpa.repository.JpaRepository;

public interface employeeTaskRepository  extends JpaRepository<EmployeeTasks, Integer > {
}
