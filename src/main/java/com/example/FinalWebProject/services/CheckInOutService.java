package com.example.FinalWebProject.services;

import com.example.FinalWebProject.dtos.CancellationRequestsDto;
import com.example.FinalWebProject.dtos.CheckInOutDto;
import com.example.FinalWebProject.entities.CancellationRequest;
import com.example.FinalWebProject.entities.CheckInOut;
import com.example.FinalWebProject.repositories.CancellationRequestRepository;
import com.example.FinalWebProject.repositories.CheckInOutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CheckInOutService {
    @Autowired
    private CheckInOutRepository checkInOutRepository;


    public CheckInOutDto updateProcess(Integer requestId, CheckInOutDto checkInOutDto) {
        Optional<CheckInOut> optionalProcess = checkInOutRepository.findById(requestId);
        if (optionalProcess.isPresent()) {
            CheckInOut process = optionalProcess.get();
            process.setProcess(CheckInOut.Process.valueOf(checkInOutDto.getProcess()));
            return CheckInOutDto.ToDto(checkInOutRepository.save(process));
        } else {
            throw new RuntimeException("zeft: " + requestId);
        }
    }
}
