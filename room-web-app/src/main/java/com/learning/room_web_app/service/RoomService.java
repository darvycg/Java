package com.learning.room_web_app.service;

import com.learning.room_web_app.data.entity.RoomEntity;
import com.learning.room_web_app.data.repository.RoomRepository;
import com.learning.room_web_app.web.model.Room;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class RoomService {

    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> getAllRooms() {
        List<RoomEntity> entities = this.roomRepository.findAll();
        List<Room> rooms = new ArrayList<>(entities.size());
        entities.forEach(e -> rooms.add(getRoomFromEntity(e)));
        return rooms;
    }

    public Room getRoomById(UUID id) {
        Optional<RoomEntity> entity = this.roomRepository.findById(id);
        return entity.map(this::getRoomFromEntity).orElse(null);
    }

    public Room addRoom(Room room) {
        RoomEntity roomEntity = this.getRoomEntityFromRoom(room);
        this.roomRepository.save(roomEntity);
        return this.getRoomFromEntity(roomEntity);
    }

    public Room updateRoom(Room room) {
        RoomEntity roomEntity = this.getRoomEntityFromRoom(room);
        this.roomRepository.save(roomEntity);
        return this.getRoomFromEntity(roomEntity);
    }

    public void deleteRoom(UUID id) {
        this.roomRepository.deleteById(id);
    }

    private Room getRoomFromEntity(RoomEntity roomEntity) {
        return new Room(roomEntity.getRoomId(), roomEntity.getName(), roomEntity.getNumber(), roomEntity.getBedInfo());
    }

    private RoomEntity getRoomEntityFromRoom(Room room) {
        return new RoomEntity(room.getId(), room.getName(), room.getNumber(), room.getInfo());
    }
}
