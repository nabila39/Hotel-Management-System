package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.Employee;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDto {
    private Integer employeeId;
    private Integer id;
    private String fullName;
    private String email;
    private String phone;
    public static EmployeeDto ToDto(Employee employee){
        return EmployeeDto.builder().
                employeeId(employee.getEmployeeId()).
                id(employee.getUser().getId()).
                fullName(employee.getFullName()).
                email(employee.getEmail()).
                phone(employee.getPhone()).
                build();

    }


}
