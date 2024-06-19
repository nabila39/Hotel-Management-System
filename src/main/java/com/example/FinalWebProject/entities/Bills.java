package com.example.FinalWebProject.entities;


import com.example.FinalWebProject.dtos.BillsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Entity
@Table(name = "Bills")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Bills {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer billId;

    @OneToOne
    @JoinColumn(name = "reservationId", foreignKey = @ForeignKey(name = "reservationId"))
    private Reservations reservation;

    @Column(nullable = false)
    private Double amount;

    private Date issueDate;

    private Date dueDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentStatus status;

    public enum PaymentStatus {
        UNPAID, PAID, CANCELLED
    }

    public static Bills toEntity(BillsDto dto) {
        Bills bills = Bills.builder()
                .billId(dto.getBillId())
                .amount(dto.getAmount())
                .issueDate(dto.getIssueDate())
                .dueDate(dto.getDueDate())
                .status(Bills.PaymentStatus.valueOf(dto.getStatus()))
                .build();
        if (dto.getReservationId() != null) {
            Reservations reservations = new Reservations();
            reservations.setReservationId(dto.getReservationId());
            bills.setReservation(reservations);
        }
        return bills;
    }
}
