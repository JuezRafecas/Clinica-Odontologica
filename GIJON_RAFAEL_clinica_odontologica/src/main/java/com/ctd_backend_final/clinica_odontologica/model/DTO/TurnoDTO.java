package com.ctd_backend_final.clinica_odontologica.model.DTO;

import com.ctd_backend_final.clinica_odontologica.model.ADTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.UUID;

@Getter @Setter
public class TurnoDTO extends ADTO {
    private UUID id;
    private Date fecha;
    @JsonIgnoreProperties({"nombre"})
    private OdontologoDTO odontologo;
    @JsonIgnoreProperties({"dni", "fechaIngreso", "domicilios"})
    private PacienteDTO paciente;
}
