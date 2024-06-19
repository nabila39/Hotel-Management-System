package com.example.FinalWebProject.repositories;
import com.example.FinalWebProject.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {
    @Query("SELECT r FROM Reservations r WHERE r.customer.id = :userId")
    List<Reservations> findByUserId(@Param("userId") Integer userId);

    @Query("SELECT r FROM Reservations r JOIN r.customer u WHERE u.fullName = :userName")
    List<Reservations> findByUserName(@Param("userName") String userName);

    @Query("SELECT r FROM Reservations r WHERE r.reservationDate = :reservationDate")
    List<Reservations> findByReservationDate(@Param("reservationDate") LocalDate reservationDate);
}
