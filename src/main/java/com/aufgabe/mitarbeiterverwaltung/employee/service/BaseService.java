package com.aufgabe.mitarbeiterverwaltung.employee.service;

public interface BaseService<DTO, ID, Repository> {

    DTO createEmployee(DTO dto);
    DTO updateEmployee(ID id, DTO dto);
    void deleteEmployee(ID id);
    DTO getEmployeeById(ID id);
}
