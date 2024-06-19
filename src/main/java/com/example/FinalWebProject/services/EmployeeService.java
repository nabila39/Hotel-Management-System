package com.example.FinalWebProject.services;
import com.example.FinalWebProject.dtos.BillsDto;
import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.entities.Bills;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.UserRepository;
import com.example.FinalWebProject.repositories.employeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    private employeeRepository employeeRepository;
    @Autowired
    private UserRepository userRepository;

    public EmployeeDto addEmployee(EmployeeDto employeeDto) throws Exception {
        Integer userId = employeeDto.getId();
        Optional<User> userOptional = userRepository.findById(userId);
        if (userOptional.isPresent()) {
            if (String.valueOf(userOptional.get().getRole()).equals("ADMIN")) {
                Employee savedEmployee = employeeRepository.save(Employee.toEntity(employeeDto));
                return EmployeeDto.ToDto(savedEmployee);
            } else {
                throw new Exception("User ID does not have admin role");
            }
        } else {
            throw new Exception("User ID not found");
        }
    }


    public List<EmployeeDto> allEmployee() {
        List<Employee> bills = employeeRepository.findAll();
        List<EmployeeDto> empolyeeDtoList = new ArrayList<>();
        for (Employee employee : bills) {
            empolyeeDtoList.add(EmployeeDto.ToDto(employee));
        }
        return empolyeeDtoList;
    }

    public EmployeeDto updateEmployee(Integer employeeId, EmployeeDto employeeDto) throws Exception {
        Optional<Employee> employeeOptional = employeeRepository.findById(employeeId);
        if (employeeOptional.isPresent()) {
            Employee employee = employeeOptional.get();
            employee.setFullName(employeeDto.getFullName());
            employee.setEmail(employeeDto.getEmail());
            employee.setPhone(employeeDto.getPhone());
            Integer userId = employeeDto.getId();
            Optional<User> userOptional = userRepository.findById(userId);
            if (userOptional.isPresent()) {
                if (String.valueOf(userOptional.get().getRole()).equals("ADMIN")) {
                    User user = userOptional.get();
                    employee.setUser(user);
                } else {
                    throw new Exception("User ID does not have admin role");
                }
            } else {
                throw new Exception("User ID not found");
            }
            Employee updatedEmployee = employeeRepository.save(employee);
            return EmployeeDto.ToDto(updatedEmployee);
        } else {
            throw new Exception("Employee not found");
        }
    }

    public void deleteEmployee(Integer employeeId) throws Exception {
        if (employeeRepository.existsById(employeeId)) {
            employeeRepository.deleteById(employeeId);
        } else {
            throw new Exception("Employee not found");
        }
    }
}
