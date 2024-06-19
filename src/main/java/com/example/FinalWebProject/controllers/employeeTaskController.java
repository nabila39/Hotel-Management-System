package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.dtos.EmployeeTasksDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.EmployeeTasks;
import com.example.FinalWebProject.services.EmployeeTasksService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/Tasks")
public class employeeTaskController {
@Autowired
    private EmployeeTasksService employeeTasksService;


    @PostMapping("/addTask")

    public ResponseEntity<?> addTask(@RequestBody EmployeeTasksDto employeeTasksDto) throws Exception {
            EmployeeTasksDto savedTask = employeeTasksService.addTask(employeeTasksDto);
            return ResponseEntity.ok(savedTask);

    }

    @PatchMapping("/changeStatus")
    public ResponseEntity<String> changeStatus(@RequestParam Integer id, @RequestBody Map<String, Object> Task) {
        employeeTasksService.changeStatus(id, Task);
        return ResponseEntity.ok("Change Status successfully");
    }

    @PutMapping("/updateTask")
    public EmployeeTasksDto updateTask(@RequestParam Integer id, @RequestBody EmployeeTasksDto employeeTasksDto) throws Exception {
        return employeeTasksService.updateTask(id, employeeTasksDto);
    }

    @DeleteMapping("/deleteTask")
    public ResponseEntity<?> deleteTask(@RequestParam Integer id) {
        try {
            employeeTasksService.deleteTask(id);
            return ResponseEntity.ok("Task deleted successfully");
        } catch (Exception e) {
            return ResponseEntity.status(400).body(e.getMessage());
        }
    }


    @GetMapping("/allTask")
    public List<EmployeeTasksDto> allTask(){
        return employeeTasksService.allTask();
    }
}
