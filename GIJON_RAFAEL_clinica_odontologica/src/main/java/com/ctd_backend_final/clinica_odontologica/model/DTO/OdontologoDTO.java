package com.ctd_backend_final.clinica_odontologica.model.DTO;

import com.ctd_backend_final.clinica_odontologica.model.ADTO;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Setter @Getter
public class OdontologoDTO extends ADTO {
    private UUID id;
    private String nombre;
    private String apellido;
    private Integer matricula;
}
