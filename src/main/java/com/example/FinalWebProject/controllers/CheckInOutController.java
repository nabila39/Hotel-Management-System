package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.CheckInOutDto;
import com.example.FinalWebProject.services.CheckInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CheckInOut")

public class CheckInOutController {
    @Autowired
    private CheckInOutService checkInOutService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/checkIn")
    public ResponseEntity<CheckInOutDto> checkIn(@RequestBody CheckInOutDto checkInOutDto) throws Exception {
        CheckInOutDto add = checkInOutService.checkIn(checkInOutDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/checkOut")
    public ResponseEntity<CheckInOutDto> checkOut(@RequestBody CheckInOutDto checkInOutDto) throws Exception {
        CheckInOutDto add = checkInOutService.checkOut(checkInOutDto);
        return new ResponseEntity<>(add, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/updateProcess")
    public ResponseEntity<CheckInOutDto> updateProcess(@RequestParam Integer requestId, @RequestBody CheckInOutDto checkInOutDto) throws Exception {
        CheckInOutDto updated = checkInOutService.updateProcess(requestId, checkInOutDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
