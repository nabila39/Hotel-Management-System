package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.EmployeeDto;
import com.example.FinalWebProject.dtos.ReservationDto;
import com.example.FinalWebProject.entities.Employee;
import com.example.FinalWebProject.entities.Reservations;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.ReservationsRepository;
import com.example.FinalWebProject.repositories.RoomRepository;
import com.example.FinalWebProject.repositories.employeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ReservationsService {
    @Autowired
    private employeeTaskRepository employeeTaskRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private ReservationsRepository reservationsRepository;
    public ReservationDto newReservations(ReservationDto reservationDto) throws Exception {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        if(String.valueOf(reservationDto.getStatus()).equals("null")){
            reservationDto.setStatus("BOOKED");
        }
        Optional<Room> roomOptional = roomRepository.findById(reservationDto.getRoomId());
        Room room=roomOptional.get();
        if(roomOptional.isPresent()){
            if(!room.isAvailable()){
                throw new Exception("This room is not available");
            }
        }
        Integer userId=currentUser.getId();
            if (String.valueOf(currentUser.getRole()).equals("USER")) {
                reservationDto.setId(userId);
                Reservations reservations=reservationsRepository.save(Reservations.toEntity(reservationDto));
                room.setAvailable(false);
                roomRepository.save(room);
                return ReservationDto.ToDto(reservations);
            } else {
                throw new Exception("User ID does not have CUSTOMER role");
            }

    }
    public ReservationDto updateReservation(Integer reservationId, ReservationDto updatedReservationDto) throws Exception {
        Optional<Reservations> optionalReservation = reservationsRepository.findById(reservationId);

        if (optionalReservation.isPresent()) {
            Reservations existingReservation = optionalReservation.get();

            existingReservation.setCheckInDate(updatedReservationDto.getCheckInDate());
            existingReservation.setCheckOutDate(updatedReservationDto.getCheckOutDate());
            Optional<Room> optionalRoom = roomRepository.findById(updatedReservationDto.getRoomId());
            if (optionalRoom.isPresent()) {
                existingReservation.setRooms(optionalRoom.get());
            } else {
                throw new Exception("Room not found for ID: " + updatedReservationDto.getRoomId());
            }

            Reservations updatedReservation = reservationsRepository.save(existingReservation);
            return ReservationDto.ToDto(updatedReservation);
        } else {
            throw new Exception("Reservation not found for ID: " + reservationId);
        }
    }
    public List<ReservationDto> findReservationsByUserId(Integer id) {
        List<Reservations> reservations = reservationsRepository.findByUserId(id);
        return reservations.stream().map(ReservationDto::ToDto).collect(Collectors.toList());
    }

    public List<ReservationDto> findReservationsByUserName(String userName) {
        List<Reservations> reservations = reservationsRepository.findByUserName(userName);
        return reservations.stream().map(ReservationDto::ToDto).collect(Collectors.toList());
    }

    public List<ReservationDto> findReservationsByReservationDate(String reservationDate) {
        LocalDate date = LocalDate.parse(reservationDate);
        List<Reservations> reservations = reservationsRepository.findByReservationDate(date);
        return reservations.stream().map(ReservationDto::ToDto).collect(Collectors.toList());
    }

}
