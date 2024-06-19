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

import java.util.Optional;
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
}
