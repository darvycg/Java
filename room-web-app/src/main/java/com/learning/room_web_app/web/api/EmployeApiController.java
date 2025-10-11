package com.learning.room_web_app.web.api;

import com.learning.room_web_app.service.EmployeeService;
import com.learning.room_web_app.web.model.Employee;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/employees")
public class EmployeApiController {

    private final EmployeeService employeeService;

    public EmployeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping
    public List<Employee> getAll() {
        return this.employeeService.getAllEmployees();
    }

    @GetMapping("/{id}")
    public Employee getById(@PathVariable("id") UUID id) {
        return this.employeeService.getEmployeeById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Employee addEmployee(@RequestBody Employee employee) {
        return this.employeeService.addEmployee(employee);
    }

    @PutMapping
    public Employee updateEmployee(@PathVariable("id") UUID id, @RequestBody Employee employee) {
        return this.employeeService.updateEmployee(employee);
    }

    @DeleteMapping
    public void deleteEmployee(@PathVariable("id") UUID id) {
        this.employeeService.deleteEmployee(id);
    }
}
