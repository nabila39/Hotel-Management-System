package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.Reservations;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ReservationDto {
    private Integer reservationId;
    private Integer id;
    private Integer roomId;
    private Date reservationDate;
    private Date checkInDate;
    private Date checkOutDate;
    private String status;

    public static ReservationDto ToDto(Reservations reservation){
        return ReservationDto.builder()
                .reservationId(reservation.getReservationId())
                .id(reservation.getCustomer().getId())
                .roomId(reservation.getRooms().getRoomId())
                .reservationDate(reservation.getReservationDate())
                .checkInDate(reservation.getCheckInDate())
                .checkOutDate(reservation.getCheckOutDate())
                .status(reservation.getStatus().name())
                .build();

    }
}
