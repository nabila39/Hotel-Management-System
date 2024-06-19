package com.example.FinalWebProject.entities;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "CancellationRequests")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor

public class CancellationRequest {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestId;
    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "UserId_fk"))
    private User user;
    @OneToOne
    @JoinColumn(name = "reservationId", foreignKey = @ForeignKey(name = "reservationId_fk"))
    private Reservations reservation;

    @CreationTimestamp
    private Date requestDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RequestStatus status;

    public enum RequestStatus {
        PENDING, APPROVED, DENIED
    }

    public static CancellationRequest toEntity(CancellationRequestsDto dto) {
        CancellationRequest cancellationRequest = CancellationRequest.builder()
                .requestId(dto.getReservationId())
                .requestDate(dto.getRequestDate())
                .status(CancellationRequest.RequestStatus.valueOf(dto.getStatus()))
                .build();
        if (dto.getReservationId() != null) {
            Reservations reservationsObj = new Reservations();
            reservationsObj.setReservationId(dto.getReservationId());
            cancellationRequest.setReservation(reservationsObj);

        }
        if (dto.getAdminId()!= null) {
            User AdminObj = new User();
            AdminObj.setId(dto.getAdminId());
            cancellationRequest.setUser(AdminObj);

        }
        return cancellationRequest;
    }
}
