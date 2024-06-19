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
    private Integer checkid;
    private Integer reservationId;
    private String process;
    private Date ProcessDate;
    private Integer AdminId;

    public static CheckInOutDto ToDto(CheckInOut checkInOut) {
        return CheckInOutDto.builder()
                .checkid(checkInOut.getCheckInOutid())
                .reservationId(checkInOut.getReservation().getReservationId())
                .process(checkInOut.getProcess().name())
                .ProcessDate(checkInOut.getProcessDate())
                .AdminId(checkInOut.getUser().getId())
                .build();
    }
}
