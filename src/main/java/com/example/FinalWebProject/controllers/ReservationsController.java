package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.ReservationDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.services.ReservationsService;
import com.example.FinalWebProject.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
