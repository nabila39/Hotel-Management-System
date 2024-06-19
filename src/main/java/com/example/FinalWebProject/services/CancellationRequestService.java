package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.Reservations;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.repositories.CancellationRequestRepository;
import com.example.FinalWebProject.repositories.ReservationsRepository;
import com.example.FinalWebProject.repositories.employeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Date;
import java.util.Optional;

@Service
public class CancellationRequestService {
    @Autowired
    private CancellationRequestRepository cancellationRequestRepository;
    @Autowired
    private ReservationsRepository reservationsRepository;

    public CancellationRequestsDto cancel(CancellationRequestsDto cancellationRequestsDto) {
       Optional<CancellationRequest> existingRequest = cancellationRequestRepository.findByReservationId(cancellationRequestsDto.getReservationId());
        if (existingRequest.isPresent()) {
            throw new RuntimeException("You have already sent a request to cancel this reservation.");
        }
        cancellationRequestsDto.setAdminId(0);
        cancellationRequestsDto.setRequestDate(new Date());
        if (String.valueOf(cancellationRequestsDto.getStatus()).equals("null")) {
            cancellationRequestsDto.setStatus("PENDING");
        }
        return CancellationRequestsDto.ToDto(cancellationRequestRepository.save(CancellationRequest.toEntity(cancellationRequestsDto)));
    }


    public ResponseEntity<?> updateStatus(Integer requestId, CancellationRequestsDto cancellationRequestsDto) {
        Optional<CancellationRequest> optionalRequest = cancellationRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            CancellationRequest request = optionalRequest.get();

            Date checkInDate = request.getReservation().getCheckInDate();
            Date currentDate = new Date();

            if (checkInDate.after(currentDate)) {
                request.getReservation().setStatus(Reservations.Status.CANCELLED);
                request.setStatus(CancellationRequest.RequestStatus.APPROVED);
                reservationsRepository.save(request.getReservation());
                CancellationRequest updatedRequest = cancellationRequestRepository.save(request);
                return ResponseEntity.ok("Reservation status updated to cancelled and request approved");
            } else {
                request.setStatus(CancellationRequest.RequestStatus.DENIED);
                CancellationRequest updatedRequest = cancellationRequestRepository.save(request);
                return ResponseEntity.ok(CancellationRequestsDto.ToDto(updatedRequest));
            }
        } else {
            throw new RuntimeException("Cancellation request not found for ID: " + requestId);
        }
    }




}
