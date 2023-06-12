package com.challenge.trimix.trimixbe.service;

import com.challenge.trimix.trimixbe.exception.errorModels.InternalServerErrorEx;
import com.challenge.trimix.trimixbe.exception.errorModels.NotFoundEx;
import com.challenge.trimix.trimixbe.model.PersonaDto;
import com.challenge.trimix.trimixbe.model.PageResponse;

public interface PersonaService {
    PersonaDto buscarPersonaPorId(int id) throws NotFoundEx;
    PageResponse<PersonaDto> buscarPersonaPorTipoDni(int page,String tipoDni) throws NotFoundEx;
    PageResponse<PersonaDto> buscarPersonaPorNombre(int page, String nombre) throws NotFoundEx;
    PageResponse<PersonaDto> listaPersonas(int page) throws NotFoundEx;
    PersonaDto crearPersona(PersonaDto persona) throws NotFoundEx, InternalServerErrorEx;
    PersonaDto modificarPersona(PersonaDto persona, int id) throws NotFoundEx;
    boolean eliminarPerosna(int id) throws NotFoundEx, InternalServerErrorEx;
}
