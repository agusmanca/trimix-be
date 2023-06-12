package com.challenge.trimix.trimixbe.dao;

import com.challenge.trimix.trimixbe.entity.PersonaEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonaDao extends JpaRepository<PersonaEntity, Integer> {
    @Query("SELECT p FROM PersonaEntity p WHERE UPPER(p.nombre) LIKE %:nombre%")
    List<PersonaEntity> findByNombre(String nombre);
    @Query("SELECT p FROM PersonaEntity p WHERE UPPER(p.nombre) LIKE %:nombre%")
    Page<PersonaEntity> findByNombre(String nombre, Pageable pageable);
    Page<PersonaEntity> findByTipoDni(String tipoDni, Pageable pageable);
}
