package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.repositories.RoomRepository;
import jakarta.transaction.Transactional;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RoomService {
    @Autowired
    private RoomRepository roomRepository;

    @Transactional
    public List<RoomDto> getAllRooms() {
        List<Room> rooms = roomRepository.findAll(); // Fetch all rooms
        // Initialize the facilities collection eagerly
        rooms.forEach(room -> {
            Hibernate.initialize(room.getFacilities());
        });
        rooms.forEach(room -> {
            Hibernate.initialize(room.getFeatures());
        });
        // Map Room entities to RoomDto objects
        List<RoomDto> roomDtos = rooms.stream()
                .map(RoomDto::roomToDto)
                .collect(Collectors.toList());

        return roomDtos;
    }
    @Transactional
    public List<RoomDto> getAllAvailableRooms() {
        List<Room> rooms = roomRepository.findAllAvailableRooms(); // Fetch all rooms
        // Initialize the facilities collection eagerly
        rooms.forEach(room -> {
            Hibernate.initialize(room.getFacilities());
        });
        rooms.forEach(room -> {
            Hibernate.initialize(room.getFeatures());
        });
        // Map Room entities to RoomDto objects
        List<RoomDto> roomDtos = rooms.stream()
                .map(RoomDto::roomToDto)
                .collect(Collectors.toList());

        return roomDtos;
    }
  /*  public RoomDto getRoomById(Integer id) {
        return roomRepository.findById(id)
                .map(RoomDto::roomToDto)
                .orElse(null);
    }
*/
    public RoomDto addRoom(@RequestBody RoomDto roomDto) {

        return RoomDto.roomToDto(roomRepository.save(Room.toEntity(roomDto)));
    }

   /* public void deleteRoomById(Integer id) {
        roomRepository.deleteById(id);
    }

    public RoomDto updateRoomPartially(Integer id, RoomDto roomDto) {
        // Implement partial update logic here
        return null;
    }

    public RoomDto updateRoomFully(Integer id, RoomDto roomDto) {
        // Implement full update logic here
        return null;
    }*/
}
