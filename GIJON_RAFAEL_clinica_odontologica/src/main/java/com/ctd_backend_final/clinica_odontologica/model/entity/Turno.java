package com.ctd_backend_final.clinica_odontologica.model.entity;

import com.ctd_backend_final.clinica_odontologica.model.AEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "turno")
@Getter @Setter
public class Turno extends AEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private Date fecha;


    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "odontologo_id")
    private Odontologo odontologo;
    @ManyToOne(cascade = CascadeType.REFRESH)
    @JoinColumn(name = "paciente_id")
    private Paciente paciente;
}
