package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.CancellationRequest;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class CancellationRequestsDto {
    private Integer requestId;
    private Integer AdminId;
    private Integer reservationId;
    private Date requestDate;
    private String status;

    public static CancellationRequestsDto ToDto(CancellationRequest cancellationRequest) {
        return CancellationRequestsDto.builder()
                .requestId(cancellationRequest.getRequestId())
                .AdminId(cancellationRequest.getUser().getId())
                .reservationId(cancellationRequest.getReservation().getReservationId())
                .status(cancellationRequest.getStatus().name())
                .requestDate(cancellationRequest.getRequestDate())
                .build();
    }
}
