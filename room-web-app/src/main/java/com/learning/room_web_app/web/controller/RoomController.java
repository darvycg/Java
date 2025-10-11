package com.learning.room_web_app.web.controller;

import com.learning.room_web_app.data.entity.RoomEntity;
import com.learning.room_web_app.data.repository.RoomRepository;
import com.learning.room_web_app.service.RoomService;
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

    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public String getRoomsPage(Model model) {
        model.addAttribute("rooms", this.roomService.getAllRooms());
        return "rooms";
    }
}
