package com.aufgabe.mitarbeiterverwaltung.employee.service;

import com.aufgabe.mitarbeiterverwaltung.employee.dto.EmployeeDto;
import com.aufgabe.mitarbeiterverwaltung.employee.model.Employee;
import com.aufgabe.mitarbeiterverwaltung.employee.repository.EmployeeRepository;
import com.aufgabe.mitarbeiterverwaltung.messaging.EmployeeEventPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
public class EmployeeService implements BaseService<EmployeeDto, UUID, EmployeeRepository> {

    private final EmployeeRepository employeeRepository;
    private final EmployeeEventPublisher publisher;

    @Override
    public EmployeeDto createEmployee(EmployeeDto dto) {
        Employee newEmployee = Employee.builder()
                .id(UUID.randomUUID())
                .name(dto.getName())
                .surname(dto.getSurname())
                .hourlyWage(dto.getHourlyWage())
                .role(dto.getRole())
                .build();

        Employee saved = employeeRepository.save(newEmployee);
        publisher.employeeCreated(toDto(saved));
        return toDto(saved);
    }

    @Override
    @Transactional
    public EmployeeDto updateEmployee(UUID id, EmployeeDto dto) {
        Employee existing = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));

        existing.setName(dto.getName());
        existing.setSurname(dto.getSurname());
        existing.setHourlyWage(dto.getHourlyWage());
        existing.setRole(dto.getRole());

        Employee saved = employeeRepository.save(existing);
        publisher.employeeUpdated(toDto(saved));
        return toDto(saved);
    }

    @Override
    public void deleteEmployee(UUID id) {
        if (!employeeRepository.existsById(id)) {
            throw new IllegalArgumentException("Employee not found: " + id);
        }
        employeeRepository.deleteById(id);
        publisher.employeeDeleted(id);
    }

    @Override
    @Transactional(readOnly = true)
    public EmployeeDto getEmployeeById(UUID id) {
        Employee e = employeeRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Employee not found: " + id));
        return toDto(e);
    }

    private EmployeeDto toDto(Employee e) {
        EmployeeDto dto = new EmployeeDto();
        dto.setId(e.getId());
        dto.setName(e.getName());
        dto.setSurname(e.getSurname());
        dto.setHourlyWage(e.getHourlyWage());
        dto.setRole(e.getRole());
        return dto;
    }
}
