package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.Bills;
import org.springframework.data.jpa.repository.JpaRepository;

public interface billsRepository extends JpaRepository<Bills, Integer> {
}
