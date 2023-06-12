package com.challenge.trimix.trimixbe.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import lombok.Data;

import java.time.LocalDate;

@Data
public class PersonaDto {
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private int id;
    private String nombre;
    private String apellido;
    @Min(value = 1111111)
    @Max(value = 99999999)
    private long dni;
    private String tipoDni;
    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate fechaNacimiento;
}
