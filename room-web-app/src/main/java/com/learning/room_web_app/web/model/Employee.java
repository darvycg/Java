package com.learning.room_web_app.web.model;

import com.learning.room_web_app.data.entity.PositionEnum;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Employee {
    private UUID employeeId;
    private String firstName;
    private String lastName;
    private String position;
}
