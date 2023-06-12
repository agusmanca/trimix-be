package com.challenge.trimix.trimixbe.util;

import com.challenge.trimix.trimixbe.entity.PersonaEntity;
import com.challenge.trimix.trimixbe.model.PageResponse;
import com.challenge.trimix.trimixbe.model.PersonaDto;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.util.List;

public class Mocks {
    public static final PersonaEntity getPersonaEntity() {
        PersonaEntity entity = new PersonaEntity();
        entity.setNombre("Testing");
        entity.setApellido("Prueba");
        entity.setTipoDni("dni");
        entity.setDni(12365476L);
        entity.setFechaNacimiento(LocalDate.of(2023, 06, 12));

        return entity;
    }

    public static final PersonaDto getPersonaDto() {
        PersonaDto dto = new PersonaDto();
        dto.setNombre("Testing");
        dto.setApellido("Prueba");
        dto.setTipoDni("dni");
        dto.setDni(12365476L);
        dto.setFechaNacimiento(LocalDate.of(2023, 06, 12));

        return dto;
    }

    public static final Page<PersonaEntity> getPageEntity() {
        return new PageImpl<>(List.of(getPersonaEntity()), PageRequest.of(0, 6), 1);
    }

    public static final Page<PersonaEntity> getEmptyPage() {
        return new PageImpl<>(List.of(), PageRequest.of(0, 6), 0);
    }

    public static final PageResponse<PersonaDto> getPersonaPage() {
        PageResponse<PersonaDto> page = new PageResponse<>();
        page.setTotalPages(1);
        page.setTotalElements(1);
        page.setSize(6);
        page.setPage(0);
        page.setContent(List.of(Mocks.getPersonaDto()));

        return page;
    }
}
