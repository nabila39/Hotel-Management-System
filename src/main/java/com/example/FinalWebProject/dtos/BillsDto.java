package com.example.FinalWebProject.dtos;


import com.example.FinalWebProject.entities.Bills;
import lombok.Builder;
import lombok.Data;

import java.util.Date;
@Builder
@Data
public class BillsDto {
    private Integer billId;
    private Integer reservationId;
    private Double amount;
    private Date issueDate;
    private Date dueDate;
    private String status;
    public static BillsDto ToDto(Bills bills){
        return BillsDto.builder()
                .billId(bills.getBillId())
                .reservationId(bills.getReservation().getReservationId())
                .amount(bills.getAmount())
                .issueDate(bills.getIssueDate())
                .dueDate(bills.getDueDate())
                .status(bills.getStatus().name())
                .build();

    }

}
