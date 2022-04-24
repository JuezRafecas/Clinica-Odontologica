package com.ctd_backend_final.clinica_odontologica.model.entity;

import com.ctd_backend_final.clinica_odontologica.model.AEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;
import java.util.Set;
import java.util.UUID;

@Entity
@Table(name = "paciente")
@Setter @Getter
public class Paciente extends AEntity {

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String nombre;
    private String apellido;
    private String dni;
    private Date fechaIngreso;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "paciente")
    private Set<Domicilio> domicilios;
}
