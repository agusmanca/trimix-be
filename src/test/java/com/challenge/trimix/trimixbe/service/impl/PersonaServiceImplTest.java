package com.challenge.trimix.trimixbe.service.impl;

import com.challenge.trimix.trimixbe.dao.PersonaDao;
import com.challenge.trimix.trimixbe.exception.errorModels.InternalServerErrorEx;
import com.challenge.trimix.trimixbe.exception.errorModels.NotFoundEx;
import com.challenge.trimix.trimixbe.model.PageResponse;
import com.challenge.trimix.trimixbe.model.PersonaDto;
import com.challenge.trimix.trimixbe.util.Mocks;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

class PersonaServiceImplTest {

    @Mock
    private PersonaDao personaDao;

    @InjectMocks
    private PersonaServiceImpl service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        ReflectionTestUtils.setField(service, "modelMapper", new ModelMapper());
    }

    @Test
    void buscarPersonaPorId_ok() throws NotFoundEx {
        when(personaDao.findById(any())).thenReturn(Optional.ofNullable(Mocks.getPersonaEntity()));
        PersonaDto response = service.buscarPersonaPorId(1);

        assertEquals(response.getNombre(), Mocks.getPersonaDto().getNombre());
        assertNotNull(response);
        verify(personaDao, times(1)).findById(any());
    }


    @Test
    void buscarPersonaPorDni_ok() throws NotFoundEx {

        when(personaDao.findByTipoDni(any(), any())).thenReturn(Mocks.getPageEntity());
        PageResponse<PersonaDto> response = service.buscarPersonaPorTipoDni(0, "dni");

        assertEquals(response.getContent().get(0).getNombre(), Mocks.getPersonaDto().getNombre());
        assertNotNull(response);
        verify(personaDao, times(1)).findByTipoDni(any(), any());
    }

    @Test
    void buscarPersonaPorDni_fail() {
        when(personaDao.findByTipoDni(any(), any())).thenReturn(Mocks.getEmptyPage());
        assertThrows(NotFoundEx.class, () -> {
            service.buscarPersonaPorTipoDni(0, "dni");
        });
    }

    @Test
    void buscarPersonaPorNombre_ok() throws NotFoundEx {
        when(personaDao.findByNombre(any(), any())).thenReturn(Mocks.getPageEntity());
        PageResponse<PersonaDto> response = service.buscarPersonaPorNombre(0, "Testing");

        assertEquals(response.getContent().get(0).getNombre(), Mocks.getPersonaDto().getNombre());
        assertEquals(response.getContent().size(), 1);
        verify(personaDao, times(1)).findByNombre(any(), any());
    }

    @Test
    void buscarPersonaPorNombre_fail() throws NotFoundEx {
        when(personaDao.findByNombre(any(), any())).thenReturn(Mocks.getEmptyPage());
        assertThrows(NotFoundEx.class, () -> {
            service.buscarPersonaPorNombre(0, "Testing");
        });
    }

    @Test
    void crearPersona() throws InternalServerErrorEx {
        when(personaDao.save(any())).thenReturn(Mocks.getPersonaEntity());
        PersonaDto response = service.crearPersona(Mocks.getPersonaDto());

        assertEquals(response.getNombre(), Mocks.getPersonaDto().getNombre());
        assertNotNull(response);
        verify(personaDao, times(1)).save(any());
    }

    @Test
    void modificarPersona() throws NotFoundEx {
        when(personaDao.findById(any())).thenReturn(Optional.ofNullable(Mocks.getPersonaEntity()));
        when(personaDao.save(any())).thenReturn(Mocks.getPersonaEntity());

        PersonaDto response = service.modificarPersona(Mocks.getPersonaDto(), 1);

        assertEquals(response.getNombre(), Mocks.getPersonaDto().getNombre());
        assertNotNull(response);
        verify(personaDao, times(1)).findById(any());
        verify(personaDao, times(1)).save(any());
    }
}