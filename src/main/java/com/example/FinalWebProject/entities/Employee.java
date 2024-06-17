package com.example.FinalWebProject.entities;


import com.example.FinalWebProject.dtos.EmployeeDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Table(name = "Employee")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer employeeId;
    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "UserId"))
    private User user;
    private String fullName;
    private String email;
    private String phone;

    public static Employee toEntity(EmployeeDto employeeDto) {
        Employee employee = Employee.builder().
                fullName(employeeDto.getFullName()).
                email(employeeDto.getEmail()).
                phone(employeeDto.getPhone())
                .build();
        if (employeeDto.getId() != null) {
            User userObj = new User();
            userObj.setId(employeeDto.getId());
            employee.setUser(userObj);
        }
        return employee;
    }



}

