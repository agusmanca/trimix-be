package com.challenge.trimix.trimixbe.controller;

import com.challenge.trimix.trimixbe.exception.errorModels.InternalServerErrorEx;
import com.challenge.trimix.trimixbe.exception.errorModels.NotFoundEx;
import com.challenge.trimix.trimixbe.model.PageResponse;
import com.challenge.trimix.trimixbe.model.PersonaDto;
import com.challenge.trimix.trimixbe.service.PersonaService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/persona")
public class PersonaController {

    @Autowired
    private PersonaService service;

    @GetMapping("/all/{page}")
    public ResponseEntity<PageResponse<PersonaDto>> listarPersonas(@NotEmpty @PathVariable int page) throws NotFoundEx {
        return new ResponseEntity<>(service.listaPersonas(page), HttpStatus.OK);
    }

    @GetMapping("/dni/{page}/{tipoDni}")
    public ResponseEntity<PageResponse<PersonaDto>> buscarPorDni(@NotEmpty @PathVariable int page,@NotBlank @PathVariable String tipoDni) throws NotFoundEx {
        return new ResponseEntity<>(service.buscarPersonaPorTipoDni(page, tipoDni), HttpStatus.OK);
    }

    @GetMapping("/name/{page}/{nombre}")
    public ResponseEntity<PageResponse<PersonaDto>> buscarPorNombre(@NotEmpty @PathVariable int page, @NotBlank @PathVariable String nombre) throws NotFoundEx {
        return new ResponseEntity<>(service.buscarPersonaPorNombre(page, nombre), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PersonaDto> buscarPorId(@NotBlank @PathVariable int id) throws NotFoundEx {
        return new ResponseEntity<>(service.buscarPersonaPorId(id), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<PersonaDto> crear(@Valid @RequestBody PersonaDto request) throws InternalServerErrorEx, NotFoundEx {
        return new ResponseEntity<>(service.crearPersona(request), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PersonaDto> actualizar(@Valid @RequestBody PersonaDto request, @NotEmpty @PathVariable int id) throws NotFoundEx {
        return new ResponseEntity<>(service.modificarPersona(request, id), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> eliminar(@NotEmpty @PathVariable int id) throws NotFoundEx, InternalServerErrorEx {
        boolean result = service.eliminarPerosna(id);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
