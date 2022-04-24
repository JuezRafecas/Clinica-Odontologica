package com.ctd_backend_final.clinica_odontologica.service;

import com.ctd_backend_final.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
public interface IService<DTO> {
    Optional<DTO> buscarPorId(UUID id);
    Set<DTO> buscarTodos();
    Optional<DTO> agregar(DTO dto) throws TurnoWithDateAlreadyPersisted;
    void actualizar(DTO dto);

    void eliminar(UUID id) throws ResourceNotFoundException;
}
