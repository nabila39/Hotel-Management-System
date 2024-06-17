package com.example.FinalWebProject.entities;

import com.example.FinalWebProject.dtos.EmployeeTasksDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Table(name = "EmployeeTasks")
@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeTasks {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer taskId;
    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "id_fk"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "employeeId"))
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "roomId", foreignKey = @ForeignKey(name = "roomId"))
    private Room rooms;
    private Date taskDate;
    private String taskDescription;
    private String status;

    public static EmployeeTasks toEntity(EmployeeTasksDto dto) {
        EmployeeTasks employeeTasks = EmployeeTasks.builder()
                .taskId(dto.getTaskId())
                .taskDate(dto.getTaskDate())
                .taskDescription(dto.getTaskDescription())
                .status(dto.getStatus())
                .build();

        if (dto.getUserId() != null) {
            User user1 = new User();
            user1.setId(dto.getUserId());
            employeeTasks.setUser(user1);
        }

        if (dto.getEmployeeId() != null) {
            Employee employee = new Employee();
            employee.setEmployeeId(dto.getEmployeeId());
            employeeTasks.setEmployee(employee);
        }

        if (dto.getRoomId() != null) {
            Room room = new Room();
            room.setRoomId(dto.getRoomId());
            employeeTasks.setRooms(room);
        }

        return employeeTasks;
    }










}
