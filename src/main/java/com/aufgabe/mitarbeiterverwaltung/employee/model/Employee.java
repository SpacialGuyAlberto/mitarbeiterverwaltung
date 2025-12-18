package com.aufgabe.mitarbeiterverwaltung.employee.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(schema = "public", name = "employees")
public class Employee {
    @Id
    @Column(name = "id")
    UUID id;

    @Column(name="name")
    String name;

    @Column(name="surname")
    String surname;

    @Column(name="hourly_wage")
    Long hourlyWage;

    @Enumerated(EnumType.STRING)
    @Column(name = "role", nullable = false)
    private Role role;
}
