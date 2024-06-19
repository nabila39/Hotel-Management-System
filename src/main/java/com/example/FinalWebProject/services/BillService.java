package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.BillsDto;
import com.example.FinalWebProject.entities.Bills;
import com.example.FinalWebProject.entities.Reservations;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.repositories.ReservationsRepository;
import com.example.FinalWebProject.repositories.RoomRepository;
import com.example.FinalWebProject.repositories.billsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class BillService {
    @Autowired
    private billsRepository billRepository;
    @Autowired
    private ReservationsRepository reservationRepository;

    @Autowired
    private RoomRepository roomRepository;


    public void addBill(BillsDto billsDto) {
        Reservations reservation = reservationRepository.findById(billsDto.getReservationId())
                .orElseThrow(() -> new IllegalArgumentException("Reservation not found"));

        Room room = roomRepository.findById(reservation.getRooms().getRoomId())
                .orElseThrow(() -> new IllegalArgumentException("Room not found"));

        long durationInMillis = Math.abs(reservation.getCheckOutDate().getTime() - reservation.getCheckInDate().getTime());
        long durationInDays = Duration.ofMillis(durationInMillis).toDays();
        double amount = durationInDays * room.getPrice();

        LocalDate dueDate = billsDto.getDueDate().toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate currentDate = LocalDate.now();

        Bills.PaymentStatus status = (dueDate.isAfter(currentDate)) ? Bills.PaymentStatus.UNPAID : Bills.PaymentStatus.PAID;

        Bills bill = Bills.builder()
                .reservation(reservation)
                .amount(amount)
                .issueDate(new Date())
                .dueDate(billsDto.getDueDate())
                .status(status)
                .build();

        billRepository.save(bill);
    }
    public List<BillsDto> getAllBills() {
        List<Bills> bills = billRepository.findAll();
        List<BillsDto> billsDtoList = new ArrayList<>();
        for (Bills bill : bills) {
            billsDtoList.add(BillsDto.ToDto(bill));
        }
        return billsDtoList;
    }




}
