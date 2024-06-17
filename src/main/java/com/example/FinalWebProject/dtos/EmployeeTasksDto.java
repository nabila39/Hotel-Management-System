package com.example.FinalWebProject.dtos;



import com.example.FinalWebProject.entities.EmployeeTasks;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTasksDto {
    private Integer taskId;
    private Integer userId;
    private Integer employeeId;
    private Integer roomId;
    private Date taskDate;
    private String taskDescription;
    private String status;

    public static EmployeeTasksDto ToDto(EmployeeTasks employeeTasks){
        return EmployeeTasksDto.builder().
                taskId(employeeTasks.getTaskId()).
                userId(employeeTasks.getUser().getId()).
                employeeId(employeeTasks.getEmployee().getEmployeeId()).
                roomId(employeeTasks.getRooms().getRoomId()).
                taskDate(employeeTasks.getTaskDate()).
                taskDescription(employeeTasks.getTaskDescription()).
                status(employeeTasks.getStatus()).
                build();

    }
}
