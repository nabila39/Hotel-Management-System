package com.example.FinalWebProject.entities;

import com.example.FinalWebProject.dtos.EmployeeTasksDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

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
    private Long taskId;
    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "UserId"))
    private User user;
    @ManyToOne
    @JoinColumn(name = "employee_id", foreignKey = @ForeignKey(name = "employeeId"))
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "roomId", foreignKey = @ForeignKey(name = "roomId"))
    private Room rooms;
    @CreationTimestamp
    private Date taskDate;
    private String taskDescription;

    @Enumerated(EnumType.STRING) private TaskStatus status;

    public enum TaskStatus { PENDING, IN_PROGRESS, COMPLETED, CANCELLED; }

    public static EmployeeTasks toEntity(EmployeeTasksDto dto) {
        EmployeeTasks employeeTasks = EmployeeTasks.builder()
                .taskDate(dto.getTaskDate())
                .taskDescription(dto.getTaskDescription())
                .status(dto.getStatus() != null ? TaskStatus.valueOf(dto.getStatus()) : null)
                .build();

        if (dto.getId() != null) {
            User user1 = new User();
            user1.setId(dto.getId());
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
