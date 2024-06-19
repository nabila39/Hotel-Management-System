package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CheckInOutRepository extends JpaRepository<CheckInOut, Integer> {
    @Query("SELECT c FROM CheckInOut c WHERE c.reservation.id = :reservationId")
     Optional<CheckInOut> findByReservationId(@Param("reservationId") Integer reservationId);
}
