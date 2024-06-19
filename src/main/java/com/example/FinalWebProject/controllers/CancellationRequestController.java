package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import com.example.FinalWebProject.dtos.ReservationDto;
import com.example.FinalWebProject.services.CancellationRequestService;
import com.example.FinalWebProject.services.ReservationsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CancellationRequest")
public class CancellationRequestController {

    @Autowired
    private CancellationRequestService cancellationRequestService;

    @PostMapping("/cancel")
    public ResponseEntity<CancellationRequestsDto> cancel(@RequestBody CancellationRequestsDto cancellationRequestsDto) throws Exception {
        CancellationRequestsDto cancelled = cancellationRequestService.cancel(cancellationRequestsDto);
        return new ResponseEntity<>(cancelled, HttpStatus.CREATED);
    }

    @PatchMapping ("/update-status")
    public ResponseEntity<CancellationRequestsDto> updateStatus(@RequestParam Integer requestId, @RequestBody CancellationRequestsDto cancellationRequestsDto) throws Exception {
        CancellationRequestsDto updated = cancellationRequestService.updateStatus(requestId, cancellationRequestsDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }

}
