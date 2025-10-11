package com.learning.room_web_app.web.api;

import com.learning.room_web_app.service.RoomService;
import com.learning.room_web_app.web.model.Room;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/rooms")
public class RoomApiController {

    private final RoomService roomService;

    public RoomApiController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> getAll() {
        return this.roomService.getAllRooms();
    }

    @GetMapping("/{id}")
    public Room getRoom(@PathVariable(name = "id") UUID id) {
        return this.roomService.getRoomById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Room addRoom(@RequestBody Room room) {
        return this.roomService.addRoom(room);
    }

    @PutMapping
    public Room updateRoom(@PathVariable(name = "id") UUID id, @RequestBody Room room) {
        return this.roomService.updateRoom(room);
    }

    @DeleteMapping
    public void deleteRoom(@PathVariable(name = "id") UUID id) {
        this.roomService.deleteRoom(id);
    }
}
