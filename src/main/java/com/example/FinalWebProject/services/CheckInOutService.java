package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.CheckInOutDto;
import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.CheckInOut;
import com.example.FinalWebProject.entities.User;
import com.example.FinalWebProject.repositories.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckInOutService {
    @Autowired
    private CheckInOutRepository checkInOutRepository;

    public  CheckInOutDto checkIn(CheckInOutDto checkInOutDto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User currentUser = (User) authentication.getPrincipal();
        checkInOutDto.setAdminId(currentUser.getId());
        checkInOutDto.setProcess("CheckIn");
        return CheckInOutDto.ToDto(checkInOutRepository.save(CheckInOut.toEntity(checkInOutDto)));

    }
    public  CheckInOutDto checkOut(CheckInOutDto checkInOutDto) {
        Optional<CheckInOut> existingRequestOptional = checkInOutRepository.findByReservationId(checkInOutDto.getReservationId());
        CheckInOut checkInOut =new CheckInOut();
        if (existingRequestOptional.isPresent()) {
            checkInOut= existingRequestOptional.get();
            checkInOut.setProcess(CheckInOut.Process.valueOf("CheckOut"));
            checkInOutRepository.save(CheckInOut.toEntity(CheckInOutDto.ToDto(checkInOut)));
        }

        return CheckInOutDto.ToDto(checkInOut);

    }



    public CheckInOutDto updateProcess(Integer requestId, CheckInOutDto checkInOutDto) {
        Optional<CheckInOut> optionalProcess = checkInOutRepository.findById(requestId);
        if (optionalProcess.isPresent()) {
            CheckInOut process = optionalProcess.get();
            process.setProcess(CheckInOut.Process.valueOf(checkInOutDto.getProcess()));
            return CheckInOutDto.ToDto(checkInOutRepository.save(process));
        } else {
            throw new RuntimeException("not found: " + requestId);
        }
    }
}
