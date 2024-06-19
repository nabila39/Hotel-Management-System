package com.example.FinalWebProject.repositories;
import com.example.FinalWebProject.entities.Reservations;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationsRepository extends JpaRepository<Reservations, Integer> {
}
