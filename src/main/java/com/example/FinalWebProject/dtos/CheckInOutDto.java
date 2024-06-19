package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.CheckInOut;
import lombok.Builder;
import lombok.Data;

import java.util.Date;


import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Data
@Builder
public class CheckInOutDto {
    private Integer id;
    private Integer reservationId;
    private String process;
    public static CheckInOutDto ToDto(CheckInOut checkInOut) {
        return CheckInOutDto.builder()
                .id(checkInOut.getId())
                .reservationId(checkInOut.getReservation().getReservationId())
                .process(checkInOut.getProcess().name())
                .build();
    }
}
