package com.example.FinalWebProject.controllers;

import com.example.FinalWebProject.dtos.BillsDto;
import com.example.FinalWebProject.services.BillService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Bills")
public class BillController {
    @Autowired
    private BillService billService;
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/addBill")
    public ResponseEntity<String> addBill(@RequestBody BillsDto billsDto) {
        billService.addBill(billsDto);
        return new ResponseEntity<>("Bill added successfully", HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/getBills")
    public ResponseEntity<List<BillsDto>> getBills() {
        List<BillsDto> bills = billService.getAllBills();
        return new ResponseEntity<>(bills, HttpStatus.OK);
    }
}
