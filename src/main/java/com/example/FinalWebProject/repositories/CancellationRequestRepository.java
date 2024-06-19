package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.CancellationRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface CancellationRequestRepository extends JpaRepository<CancellationRequest, Integer> {



    @Query("SELECT c FROM CancellationRequest c WHERE c.reservation.id = :reservationId")
    Optional<CancellationRequest> findByReservationId(@Param("reservationId") Integer reservationId);
}
