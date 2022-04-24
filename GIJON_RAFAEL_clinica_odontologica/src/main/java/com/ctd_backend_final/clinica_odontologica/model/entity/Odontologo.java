package com.ctd_backend_final.clinica_odontologica.model.entity;

import com.ctd_backend_final.clinica_odontologica.model.AEntity;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.UUID;

@Entity
@Table(name = "odontologo")
@Setter @Getter
public class Odontologo extends AEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(
            name = "UUID",
            strategy = "org.hibernate.id.UUIDGenerator"
    )
    private UUID id;
    private String nombre;
    private String apellido;
    private Integer matricula;
}
