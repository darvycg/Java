package com.learning.room_web_app.service;

import com.learning.room_web_app.data.entity.EmployeesEntity;
import com.learning.room_web_app.data.entity.PositionEnum;
import com.learning.room_web_app.data.repository.EmployeesRepository;
import com.learning.room_web_app.web.model.Employee;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeService {

    private final EmployeesRepository employeesRepository;

    public EmployeeService(EmployeesRepository employeesRepository) {
        this.employeesRepository = employeesRepository;
    }

    public List<Employee> getAllEmployees() {
        List<EmployeesEntity> entities = this.employeesRepository.findAll();
        List<Employee> employees = new ArrayList<>(entities.size());
        entities.forEach(e -> employees.add(this.getEmployeeFromEntity(e)));
        return employees;
    }

    public Employee getEmployeeById(UUID id) {
        Optional<EmployeesEntity> entity = this.employeesRepository.findById(id);
        return entity.map(this::getEmployeeFromEntity).orElse(null);
    }

    public Employee addEmployee(Employee employee) {
        EmployeesEntity entity = this.getEmployeeEntityFromEmployee(employee);
        this.employeesRepository.save(entity);
        return this.getEmployeeFromEntity(entity);
    }

    public Employee updateEmployee(Employee employee) {
        EmployeesEntity entity = this.getEmployeeEntityFromEmployee(employee);
        this.employeesRepository.save(entity);
        return this.getEmployeeFromEntity(entity);
    }

    public void deleteEmployee(UUID id) {
        this.employeesRepository.deleteById(id);
    }

    private Employee getEmployeeFromEntity(EmployeesEntity employeesEntity) {
        return new Employee(employeesEntity.getEmployeeId(), employeesEntity.getFirstName(), employeesEntity.getLastName(), employeesEntity.getPosition().toString());
    }

    private EmployeesEntity getEmployeeEntityFromEmployee(Employee employee) {
        return new EmployeesEntity(employee.getEmployeeId(), employee.getFirstName(), employee.getLastName(), PositionEnum.valueOf(employee.getPosition()));
    }
}
