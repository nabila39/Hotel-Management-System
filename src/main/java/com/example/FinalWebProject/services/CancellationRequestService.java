package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import com.example.FinalWebProject.dtos.RoomDto;
import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.Room;
import com.example.FinalWebProject.repositories.CancellationRequestRepository;
import com.example.FinalWebProject.repositories.employeeTaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class CancellationRequestService {
    @Autowired
    private CancellationRequestRepository cancellationRequestRepository;

    public CancellationRequestsDto cancel(CancellationRequestsDto cancellationRequestsDto) {
       Optional<CancellationRequest> existingRequest = cancellationRequestRepository.findByReservationId(cancellationRequestsDto.getReservationId());
        if (existingRequest.isPresent()) {
            throw new RuntimeException("You have already sent a request to cancel this reservation.");
        }

        cancellationRequestsDto.setAdminId(0);
        if (String.valueOf(cancellationRequestsDto.getStatus()).equals("null")) {
            cancellationRequestsDto.setStatus("PENDING");
        }

        return CancellationRequestsDto.ToDto(cancellationRequestRepository.save(CancellationRequest.toEntity(cancellationRequestsDto)));
    }


    public CancellationRequestsDto updateStatus(Integer requestId, CancellationRequestsDto cancellationRequestsDto) {
        Optional<CancellationRequest> optionalRequest = cancellationRequestRepository.findById(requestId);
        if (optionalRequest.isPresent()) {
            CancellationRequest request = optionalRequest.get();
            request.setStatus(CancellationRequest.RequestStatus.valueOf(cancellationRequestsDto.getStatus()));
            return CancellationRequestsDto.ToDto(cancellationRequestRepository.save(request));
        } else {
            throw new RuntimeException("Cancellation request not found for ID: " + requestId);
        }
    }

}
