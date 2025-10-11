package com.learning.room_web_app.data.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Table(name = "EMPLOYEES")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EmployeesEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "EMPLOYEE_ID")
    private UUID employeeId;

    @Column(name = "LAST_NAME")
    private String lastName;

    @Column(name = "FIRST_NAME")
    private String firstName;

    @Column(name = "POSITION")
    @Enumerated(EnumType.STRING)
    private PositionEnum position;

}

