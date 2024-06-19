package com.example.FinalWebProject.entities;


import com.example.FinalWebProject.dtos.CheckInOutDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reservationId", foreignKey = @ForeignKey(name = "reservationId_fk1"))
    private Reservations reservation;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Process process;

    public enum Process {
        CheckIn, CheckOut
    }

    public static CheckInOut toEntity(CheckInOutDto checkInOutDto) {
        CheckInOut checkInOut = CheckInOut.builder().
                id(checkInOutDto.getId())
                .process(CheckInOut.Process.valueOf(checkInOutDto.getProcess()))
                .build();
        if (checkInOutDto.getReservationId() != null) {
            Reservations reservationObj = new Reservations();
            reservationObj.setReservationId(checkInOutDto.getReservationId());
            checkInOut.setReservation(reservationObj);
        }
        return checkInOut;
    }
}
