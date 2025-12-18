package com.aufgabe.mitarbeiterverwaltung.employee.controller;

import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RequestMapping("/employee")
public interface Controller<DTO> {
    @PostMapping("/create")
    DTO createEmployee(@RequestBody DTO dto);
    @GetMapping("/{id}")
    DTO findEmployee(@PathVariable UUID id);
    @PutMapping("/edit")
    DTO updateEmployee(@RequestBody DTO dto);
    @DeleteMapping("/delete/{id}")
    void deleteEmployee(@PathVariable UUID id);
}
