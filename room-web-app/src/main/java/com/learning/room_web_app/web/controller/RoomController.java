package com.learning.room_web_app.web.controller;

import com.learning.room_web_app.data.entity.RoomEntity;
import com.learning.room_web_app.data.repository.RoomRepository;
import com.learning.room_web_app.web.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/rooms")
public class RoomController {

    private final RoomRepository roomRepository;

    public RoomController(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    @GetMapping
    public String getRoomsPage(Model model) {
        List<RoomEntity> roomEntities = this.roomRepository.findAll();
        List<Room> rooms = new ArrayList<>(roomEntities.size());
        roomEntities.forEach(e -> rooms.add(new Room(e.getRoomId(), e.getName(), e.getNumber(), e.getBedInfo())));
        model.addAttribute("rooms", rooms);
        return "rooms";
    }
}
