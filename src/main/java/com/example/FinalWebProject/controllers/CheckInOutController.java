package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import com.example.FinalWebProject.dtos.CheckInOutDto;
import com.example.FinalWebProject.services.CancellationRequestService;
import com.example.FinalWebProject.services.CheckInOutService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/CheckInOut")

public class CheckInOutController {
    @Autowired
    private CheckInOutService checkInOutService;



    @PatchMapping("/updateProcess")
    public ResponseEntity<CheckInOutDto> updateProcess(@RequestParam Integer requestId, @RequestBody CheckInOutDto checkInOutDto) throws Exception {
        CheckInOutDto updated = checkInOutService.updateProcess(requestId, checkInOutDto);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }
}
