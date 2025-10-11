package com.learning.room_web_app.async;

import lombok.Data;

import java.util.UUID;

@Data
public class AsyncPayload {
    private UUID id;
    private String model;
}
