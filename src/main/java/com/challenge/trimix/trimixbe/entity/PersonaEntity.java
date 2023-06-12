package com.challenge.trimix.trimixbe.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Entity
@Table(name = "PERSONAS")
@Data
public class PersonaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID", nullable = false)
    private int id;

    @Column(name = "NOMBRE")
    private String nombre;

    @Column(name = "APELLIDO")
    private String apellido;

    @Column(name = "DNI")
    private long dni;

    @Column(name = "TIPO_DNI")
    private String tipoDni;

    @Column(name = "FECHA_NACIMIENTO")
    private LocalDate fechaNacimiento;
}
