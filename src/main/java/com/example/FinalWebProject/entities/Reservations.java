package com.example.FinalWebProject.entities;

import com.example.FinalWebProject.dtos.ReservationDto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import java.util.Date;

@Entity
@Table(name = "Reservations")
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Reservations {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer reservationId;

    @ManyToOne
    @JoinColumn(name = "id", foreignKey = @ForeignKey(name = "customerId"))
    private User customer;

    @ManyToOne
    @JoinColumn(name = "roomId", foreignKey = @ForeignKey(name = "fk_reservation_room"))
    private Room rooms;

    @CreationTimestamp
    private Date reservationDate;

    private Date checkInDate;
    private Date checkOutDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Reservations.Status status = Status.BOOKED;

    public enum Status {
        BOOKED, CANCELLED, CHECKED_IN, CHECKED_OUT
    }

    public static Reservations toEntity(ReservationDto dto) {
        Reservations reservation = Reservations.builder()
                .reservationId(dto.getReservationId())
                .reservationDate(dto.getReservationDate())
                .checkInDate(dto.getCheckInDate())
                .checkOutDate(dto.getCheckOutDate())
                .status(Reservations.Status.valueOf(dto.getStatus()))
                .build();
        if (dto.getId() != null) {
            User customer = new User();
            customer.setId(dto.getId());
            reservation.setCustomer(customer);
        }
        if (dto.getRoomId() != null) {
            Room room = new Room();
            room.setRoomId(dto.getRoomId());
            reservation.setRooms(room);
        }
        return reservation;
    }
}
