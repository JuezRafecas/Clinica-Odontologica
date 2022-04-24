package com.ctd_backend_final.clinica_odontologica.model.DTO;

import com.ctd_backend_final.clinica_odontologica.model.ADTO;
import com.fasterxml.jackson.annotation.*;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
public class DomicilioDTO extends ADTO {
    private UUID id;
    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private Boolean principal;

    @JsonIncludeProperties({"id"})
    private PacienteDTO paciente;
}
