package com.ctd_backend_final.clinica_odontologica.repository;

import com.ctd_backend_final.clinica_odontologica.model.entity.Odontologo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface IOdontologoRepository extends JpaRepository<Odontologo, UUID> {
}
