package com.ctd_backend_final.clinica_odontologica.model.DTO;

import com.ctd_backend_final.clinica_odontologica.model.ADTO;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Setter @Getter
public class PacienteDTO extends ADTO {
    private UUID id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;
    //@JsonIgnore
    private Set<DomicilioDTO> domicilios;
}
