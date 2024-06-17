package com.example.FinalWebProject.dtos;

import com.example.FinalWebProject.entities.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RoomDto {
    private Integer id;
    private String roomNumber;
    private String details;
    private Double price;
    private List<String> facilities;
    private Integer capacity;
    private String size;
    private List<String> features;
    private boolean available;

    public static RoomDto roomToDto(Room room) {
        return RoomDto.builder()
                .id(room.getId())
                .roomNumber(room.getRoomNumber())
                .details(room.getDetails())
                .price(room.getPrice())
                .facilities(room.getFacilities())
                .capacity(room.getCapacity())
                .size(room.getSize())
                .features(room.getFeatures())
                .available(room.isAvailable())
                .build();
    }
}
