package com.ctd_backend_final.clinica_odontologica.service.implementation;

import com.ctd_backend_final.clinica_odontologica.model.DTO.PacienteDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Domicilio;
import com.ctd_backend_final.clinica_odontologica.model.entity.Paciente;
import com.ctd_backend_final.clinica_odontologica.repository.IPacienteRepository;
import com.ctd_backend_final.clinica_odontologica.service.AService;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class PacienteService extends AService<Paciente, PacienteDTO, IPacienteRepository> {
    public PacienteService() {
        super.setDtoClazz(PacienteDTO.class);
        super.setEntityClazz(Paciente.class);
    }

    @Override
    public Optional<PacienteDTO> agregar(PacienteDTO dto) {
        Paciente paciente = super.getMapper().convertValue(dto, Paciente.class);
        if (dto.getDomicilios() != null) {
            for (Domicilio domicilio : paciente.getDomicilios()) {
                domicilio.setPaciente(paciente);
            }
        }
        Paciente pacienteGuardado = super.getRepository().save(paciente);
        return Optional.of(super.getMapper().convertValue(pacienteGuardado, PacienteDTO.class));
    }
}