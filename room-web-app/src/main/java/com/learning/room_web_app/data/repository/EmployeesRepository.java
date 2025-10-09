package com.learning.room_web_app.data.repository;

import com.learning.room_web_app.data.entity.EmployeesEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface EmployeesRepository extends JpaRepository<EmployeesEntity, UUID> {
}
