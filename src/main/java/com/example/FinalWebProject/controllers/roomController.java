package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rooms")
public class roomController {

    @Autowired
    private RoomService roomService;

    @GetMapping("/getAll")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<RoomDto>> getAllRooms() {
        List<RoomDto> rooms = roomService.getAllRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }
    @GetMapping("/AllAvailableRooms")
    public ResponseEntity<List<RoomDto>> findAllAvailableRooms() {
        List<RoomDto> rooms = roomService.getAllAvailableRooms();
        return new ResponseEntity<>(rooms, HttpStatus.OK);
    }

/*
    @GetMapping("/getById")
    public RoomDto getRoomById(@RequestParam Integer id) {
        return roomService.getRoomById(id);
    }
*/

    @PostMapping("/add")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<RoomDto> addRoom(@RequestBody RoomDto roomDto) {
        RoomDto addedRoom = roomService.addRoom(roomDto);
        return new ResponseEntity<>(addedRoom, HttpStatus.CREATED);
    }

  /*  @DeleteMapping("/deleteById")
    public void deleteRoomById(@RequestParam Integer id) {
        roomService.deleteRoomById(id);
    }

    @PatchMapping("/updatePartially")
    public RoomDto updateRoomPartially(@RequestParam Integer id, @RequestBody RoomDto roomDto) {
        return roomService.updateRoomPartially(id, roomDto);
    }

    @PutMapping("/updateFully")
    public RoomDto updateRoomFully(@RequestParam Integer id, @RequestBody RoomDto roomDto) {
        return roomService.updateRoomFully(id, roomDto);
    }*/
}
