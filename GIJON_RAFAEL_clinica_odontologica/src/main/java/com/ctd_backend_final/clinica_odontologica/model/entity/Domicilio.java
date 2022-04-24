package com.ctd_backend_final.clinica_odontologica.model.entity;

import com.ctd_backend_final.clinica_odontologica.model.AEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.UUID;

@Entity
@Table(name = "domicilio")
@Setter @Getter

public class Domicilio extends AEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;

    private String calle;
    private String numero;
    private String localidad;
    private String provincia;
    private boolean principal;

    // private String paciente_id;
    @ManyToOne
    @JoinColumn(name = "paciente_id")
    @JsonIgnoreProperties("domicilios")
    private Paciente paciente;

}
