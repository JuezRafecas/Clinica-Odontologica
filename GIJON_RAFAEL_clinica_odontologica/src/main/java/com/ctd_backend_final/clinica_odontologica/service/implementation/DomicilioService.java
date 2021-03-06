package com.ctd_backend_final.clinica_odontologica.service.implementation;

import com.ctd_backend_final.clinica_odontologica.model.DTO.DomicilioDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Domicilio;
import com.ctd_backend_final.clinica_odontologica.repository.IDomicilioRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class DomicilioService {
    private IDomicilioRepository repository;
    private ObjectMapper mapper;
    @Autowired
    public void setRepository(IDomicilioRepository repository) {
        this.repository = repository;
    }
    @Autowired
    public void setMapper(ObjectMapper mapper) {
        this.mapper = mapper;
    }

    public Optional<DomicilioDTO> guardar(DomicilioDTO domicilioDTO) {
        Domicilio domicilio = mapper.convertValue(domicilioDTO, Domicilio.class);
        Domicilio domicilioGuardado = repository.save(domicilio);
        return Optional.of(mapper.convertValue(domicilioGuardado, DomicilioDTO.class));
    }

}
