package com.aufgabe.mitarbeiterverwaltung.employee.controller;

import com.aufgabe.mitarbeiterverwaltung.employee.dto.EmployeeDto;
import com.aufgabe.mitarbeiterverwaltung.employee.service.EmployeeService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/employee")
public class EmployeeController implements Controller<EmployeeDto> {
    @Autowired
    private final EmployeeService employeeService;

    @Override
    public EmployeeDto createEmployee(EmployeeDto employeeDto) {
        employeeService.createEmployee(employeeDto);
        return employeeDto;
    }

    @Override
    public EmployeeDto findEmployee(UUID id) {
       return employeeService.getEmployeeById(id);
    }

    @Override
    public EmployeeDto updateEmployee(EmployeeDto employeeDto) {
        return employeeService.updateEmployee(employeeDto.getId(), employeeDto);
    }

    @Override
    public void deleteEmployee(UUID id) {
        employeeService.deleteEmployee(id);
    }
}
