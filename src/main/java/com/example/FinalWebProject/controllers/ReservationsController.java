package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.ReservationDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.services.ReservationsService;
import com.example.FinalWebProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/reservations")
public class ReservationsController {
    @Autowired
    private ReservationsService reservationsService;
    @PostMapping("/newReservations")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<ReservationDto> addReservations(@RequestBody ReservationDto reservationDto) throws Exception {
        ReservationDto addedReservation = reservationsService.newReservations(reservationDto);
        return new ResponseEntity<>(addedReservation, HttpStatus.CREATED);
    }
    @PutMapping("/updateReservation")
    public ResponseEntity<?> updateReservation(@RequestParam Integer reservationId, @RequestBody ReservationDto updatedReservationDto) {
        try {
            ReservationDto updatedReservation = reservationsService.updateReservation(reservationId, updatedReservationDto);
            return ResponseEntity.ok(updatedReservation);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/byUserId")
    public List<ReservationDto> findReservationsByUserId(@RequestParam Integer userId) {
        return reservationsService.findReservationsByUserId(userId);
    }

    @GetMapping("/byUserName")
    public List<ReservationDto> findReservationsByUserName(@RequestParam String userName) {
        return reservationsService.findReservationsByUserName(userName);
    }

    @GetMapping("/byDate")
    public List<ReservationDto> findReservationsByReservationDate(@RequestParam String reservationDate) {
        return reservationsService.findReservationsByReservationDate(reservationDate);
    }

}
