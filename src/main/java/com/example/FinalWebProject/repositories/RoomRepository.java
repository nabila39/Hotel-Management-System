package com.example.FinalWebProject.repositories;

import com.example.FinalWebProject.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.available = true")
    List<Room> findAllAvailableRooms();
}
