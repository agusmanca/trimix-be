package com.challenge.trimix.trimixbe.service.impl;

import com.challenge.trimix.trimixbe.dao.PersonaDao;
import com.challenge.trimix.trimixbe.entity.PersonaEntity;
import com.challenge.trimix.trimixbe.exception.errorModels.InternalServerErrorEx;
import com.challenge.trimix.trimixbe.exception.errorModels.NotFoundEx;
import com.challenge.trimix.trimixbe.model.PageResponse;
import com.challenge.trimix.trimixbe.model.PersonaDto;
import com.challenge.trimix.trimixbe.service.PersonaService;
import org.apache.logging.log4j.util.Strings;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.challenge.trimix.trimixbe.config.Constantes.*;

@Service
public class PersonaServiceImpl implements PersonaService {

    @Autowired
    private PersonaDao personaDao;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public PersonaDto buscarPersonaPorId(int id) throws NotFoundEx {

        Optional<PersonaEntity> pers = personaDao.findById(id);
        if(pers.isEmpty()){
            throw new NotFoundEx(PERSONA_NOT_FOUND_BY_DNI);
        }

        return modelMapper.map(pers.get(), PersonaDto.class);
    }

    @Override
    public PageResponse<PersonaDto> buscarPersonaPorTipoDni(int page, String tipoDni) throws NotFoundEx {
        Page<PersonaEntity> pers = personaDao.findByTipoDni(tipoDni, PageRequest.of(page, 6));
        if(pers.getTotalElements() == 0){
            throw new NotFoundEx(PERSONA_NOT_FOUND_BY_DNI);
        }

        return this.setPageableResponse(pers, PersonaDto.class);
    }

    @Override
    public PageResponse<PersonaDto> buscarPersonaPorNombre(int page, String nombre) throws NotFoundEx {
        Page<PersonaEntity> pers = personaDao.findByNombre(nombre.toUpperCase(), PageRequest.of(page, 6));
        if(pers.getTotalElements() == 0){
            throw new NotFoundEx(PERSONA_NOT_FOUND_BY_NOMBRE);
        }

        return this.setPageableResponse(pers, PersonaDto.class);
    }

    @Override
    public PageResponse<PersonaDto> listaPersonas(int page) throws NotFoundEx {
        Page<PersonaEntity> pers = personaDao.findAll(PageRequest.of(page, 6));
        if(pers.getTotalElements() == 0){
            throw new NotFoundEx(PERSONA_NOT_FOUND_LST);
        }

        return this.setPageableResponse(pers, PersonaDto.class);
    }

    private <T, S> PageResponse<S> setPageableResponse(Page<T> page, Class<S> classParam) {
        PageResponse<S> response = new PageResponse<>();
        response.setTotalPages(page.getTotalPages());
        response.setTotalElements(page.getTotalElements());
        response.setSize(page.getPageable().getPageSize());
        response.setPage(page.getPageable().getPageNumber());

        List<S> contentLst = page.getContent()
                                    .stream()
                                    .map(p -> modelMapper.map(p, classParam))
                                    .collect(Collectors.toList());

        response.setContent(contentLst);

        return response;
    }

    @Override
    public PersonaDto crearPersona(PersonaDto persona) throws InternalServerErrorEx {
        Optional<PersonaEntity> newPersona = Optional.ofNullable(personaDao.save(modelMapper.map(persona, PersonaEntity.class)));
        if(newPersona.isEmpty()){
            throw new InternalServerErrorEx(PERSONA_ERROR_PERSISITENCIA);
        }

        return modelMapper.map(newPersona.get(), PersonaDto.class);
    }

    @Override
    public PersonaDto modificarPersona(PersonaDto dto, int id) throws NotFoundEx {
        Optional<PersonaEntity> entity = personaDao.findById(id);
        if(entity.isEmpty()){
            throw new NotFoundEx(PERSONA_NO_ENCONTRADA_UPDATE);
        }

        PersonaEntity updtEntity = personaDao.save(mapperUpdt(entity.get(), dto));

        return modelMapper.map(updtEntity, PersonaDto.class);
    }

    private PersonaEntity mapperUpdt(PersonaEntity entity, PersonaDto dto) {
        entity.setNombre(
            (Strings.isEmpty(dto.getNombre())) ? entity.getNombre() : dto.getNombre()
        );
        entity.setApellido(
            (Strings.isEmpty(dto.getApellido())) ? entity.getApellido() : dto.getApellido()
        );
        entity.setDni(
            (Objects.isNull(dto.getDni())) ? entity.getDni() : dto.getDni()
        );
        entity.setTipoDni(
            (Strings.isEmpty(dto.getTipoDni())) ? entity.getTipoDni() : dto.getTipoDni()
        );
        return entity;
    }

    @Override
    public boolean eliminarPerosna(int id) throws NotFoundEx, InternalServerErrorEx {
        Optional<PersonaEntity> findPerosna = personaDao.findById(id);
        if(findPerosna.isEmpty()) {
            throw new NotFoundEx(PERSONA_NO_ENCONTRADA_DELETE);
        }

        personaDao.deleteById(id);

        findPerosna = personaDao.findById(id);
        if(findPerosna.isPresent()) {
            throw new InternalServerErrorEx(PERSONA_ERROR_DELETE);
        }

        return true;
    }
}
