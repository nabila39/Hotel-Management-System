package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.CheckInOut;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CheckInOutRepository extends JpaRepository<CheckInOut, Integer> {
}
