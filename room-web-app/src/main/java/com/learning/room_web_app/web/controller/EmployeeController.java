package com.learning.room_web_app.web.controller;

import com.learning.room_web_app.data.entity.EmployeesEntity;
import com.learning.room_web_app.data.entity.RoomEntity;
import com.learning.room_web_app.data.repository.EmployeesRepository;
import com.learning.room_web_app.web.model.Employee;
import com.learning.room_web_app.web.model.Room;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private final EmployeesRepository employeesRepository;

    public EmployeeController(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    @GetMapping
    public String getEmployeesPage(Model model) {
        List<EmployeesEntity> employeesEntities = this.employeesRepository.findAll();
        List<Employee> employees = new ArrayList<>(employeesEntities.size());
        employeesEntities.forEach(e -> employees.add(new Employee(e.getEmployeeId(), e.getFirstName(), e.getLastName(), e.getPosition().toString())));
        model.addAttribute("employees", employees);
        return "employees";
    }

}
