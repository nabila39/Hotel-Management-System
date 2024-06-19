package com.example.FinalWebProject.entities;


import com.example.FinalWebProject.dtos.CheckInOutDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "CheckInOut")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CheckInOut {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer CheckInOutid;

    @OneToOne
    @JoinColumn(name = "reservationId", foreignKey = @ForeignKey(name = "reservationId_fk1"))
    private Reservations reservation;

    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "AdminId"))
    private User user;

    @CreationTimestamp
    private Date ProcessDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Process process;

    public enum Process {
        CheckIn, CheckOut
    }

    public static CheckInOut toEntity(CheckInOutDto checkInOutDto) {
        CheckInOut checkInOut = CheckInOut.builder().
                CheckInOutid(checkInOutDto.getCheckid())
                .process(CheckInOut.Process.valueOf(checkInOutDto.getProcess()))
                .ProcessDate(checkInOutDto.getProcessDate())

                .build();
        if (checkInOutDto.getReservationId() != null) {
            Reservations reservationObj = new Reservations();
            reservationObj.setReservationId(checkInOutDto.getReservationId());
            checkInOut.setReservation(reservationObj);
        }
        if (checkInOutDto.getAdminId() != null) {
            User AdminObj = new User();
            AdminObj.setId(checkInOutDto.getAdminId());
            checkInOut.setUser(AdminObj);
        }
        return checkInOut;
    }
}
