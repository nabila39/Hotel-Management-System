package com.example.FinalWebProject.entities;
import com.example.FinalWebProject.dtos.RoomDto;
import jakarta.persistence.*;
import lombok.Data;
import java.util.List;

@Data
@Entity
@Table(name = "rooms")
public class Room {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String roomNumber;

    @Column(length = 500)
    private String details;

    @Column(nullable = false)
    private double price;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "room_facilities", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "facility")
    private List<String> facilities;

    @Column(nullable = false)
    private int capacity;

    @Column(nullable = false)
    private String size;
    @Column(nullable = false)
    private boolean available;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "room_features", joinColumns = @JoinColumn(name = "room_id"))
    @Column(name = "feature")
    private List<String> features;

    public static Room toEntity(RoomDto roomdto) {
        Room room = new Room();
        room.setRoomNumber(roomdto.getRoomNumber());
        room.setDetails(roomdto.getDetails());
        room.setPrice(roomdto.getPrice());
        room.setFacilities(roomdto.getFacilities());
        room.setCapacity(roomdto.getCapacity());
        room.setSize(roomdto.getSize());
        room.setFeatures(roomdto.getFeatures());
        room.setAvailable(true);
        return room;
    }

}
