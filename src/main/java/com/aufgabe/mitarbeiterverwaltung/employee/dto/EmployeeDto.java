package com.aufgabe.mitarbeiterverwaltung.employee.dto;

import com.aufgabe.mitarbeiterverwaltung.employee.model.Role;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class EmployeeDto {
    UUID id;
    String name;
    String surname;
    Long hourlyWage;
    Role role;
}
