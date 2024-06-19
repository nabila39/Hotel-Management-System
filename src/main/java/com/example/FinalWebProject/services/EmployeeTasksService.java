package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.dtos.EmployeeTasksDto;
import com.example.FinalWebProject.dtos.RegisterUserDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.EmployeeTasks;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.RoomRepository;
import com.example.FinalWebProject.repositories.UserRepository;
import com.example.FinalWebProject.repositories.employeeRepository;
import com.example.FinalWebProject.repositories.employeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EmployeeTasksService {

    @Autowired
    private employeeTaskRepository employeeTaskRepository;
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private employeeRepository employeeRepository;

    @Autowired
    private RoomRepository roomRepository;

    public EmployeeTasksDto addTask(EmployeeTasksDto employeeTasksDto) throws Exception {
        Integer userId = employeeTasksDto.getId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            if (String.valueOf(userOptional.get().getRole()).equals("ADMIN")) {
                EmployeeTasks savedTask = employeeTaskRepository.save(EmployeeTasks.toEntity(employeeTasksDto));
                return EmployeeTasksDto.ToDto(savedTask);
            } else {
                throw new Exception("User ID does not have admin role");
            }
        } else {
            throw new Exception("User ID not found");
        }
    }

    public EmployeeTasksDto changeStatus(Integer id, Map<String, Object> taskInfo) {
        Optional<EmployeeTasks> taskOptional = employeeTaskRepository.findById(id);
        if (taskOptional.isPresent()) {
            EmployeeTasks task = taskOptional.get();

            if (taskInfo.containsKey("status")) {
                String newStatus = (String) taskInfo.get("status");
                task.setStatus(EmployeeTasks.TaskStatus.valueOf(newStatus));
            }
            EmployeeTasks savedTask = employeeTaskRepository.save(task);
            return EmployeeTasksDto.ToDto(savedTask);
        } else {
            throw new IllegalArgumentException("Task with ID " + id + " not found");
        }
    }

    public EmployeeTasksDto updateTask(Integer TaskId, EmployeeTasksDto employeeTasksDto) throws Exception {
        Optional<EmployeeTasks> TaskOptional = employeeTaskRepository.findById(TaskId);
        if (TaskOptional.isPresent()) {
            EmployeeTasks Task = TaskOptional.get();
            Optional<Employee> employeeOptional = employeeRepository.findById(employeeTasksDto.getEmployeeId());
            Employee employee =employeeOptional.get();
            Task.setEmployee(employee);
            Task.setStatus(EmployeeTasks.TaskStatus.valueOf(employeeTasksDto.getStatus()));
            Task.setTaskDescription(employeeTasksDto.getTaskDescription());
            Optional<Room> roomOptional = roomRepository.findById(employeeTasksDto.getRoomId());
            Room room =roomOptional.get();
            Task.setRooms(room);
            Integer userId = employeeTasksDto.getId();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                if (String.valueOf(userOptional.get().getRole()).equals("ADMIN")) {
                    User user = userOptional.get();
                    Task.setUser(user);
                } else {
                    throw new Exception("User ID does not have admin role");
                }
            } else {
                throw new Exception("User ID not found");
            }
            EmployeeTasks updatedTask = employeeTaskRepository.save(Task);
            return EmployeeTasksDto.ToDto(updatedTask);
        } else {
            throw new Exception("Employee not found");
        }
    }
    public void deleteTask(Integer TaskId) throws Exception {
        if (employeeTaskRepository.existsById(TaskId)) {
            employeeTaskRepository.deleteById(TaskId);
        } else {
            throw new Exception("Task not found");
        }
    }
    public List<EmployeeTasksDto> allTask() {
        List<EmployeeTasks> Tasks = this.employeeTaskRepository.findAll();
        return Tasks.stream()
                .map(EmployeeTasksDto::ToDto)
                .collect(Collectors.toList());
    }
}
