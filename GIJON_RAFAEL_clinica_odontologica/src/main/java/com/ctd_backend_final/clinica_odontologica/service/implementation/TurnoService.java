package com.ctd_backend_final.clinica_odontologica.service.implementation;

import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.PacienteDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.TurnoDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Turno;
import com.ctd_backend_final.clinica_odontologica.repository.ITurnoRepository;
import com.ctd_backend_final.clinica_odontologica.service.AService;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class TurnoService extends AService<Turno, TurnoDTO, ITurnoRepository> {


    public TurnoService() {
        super.setDtoClazz(TurnoDTO.class);
        super.setEntityClazz(Turno.class);
    }


    @Override
    public Optional<TurnoDTO> agregar(TurnoDTO turnoDTO) throws TurnoWithDateAlreadyPersisted {
        PacienteDTO paciente = turnoDTO.getPaciente();
        OdontologoDTO odontologo = turnoDTO.getOdontologo();

        Set<TurnoDTO> turnosPaciente = this.findByPatient(paciente.getId());
        Set<TurnoDTO> turnosOdontologo = this.findByOdontologo(odontologo.getId());

        for (TurnoDTO turno : turnosPaciente) {
            if (turno.getFecha().equals(turnoDTO.getFecha())) {
                throw new TurnoWithDateAlreadyPersisted();
            }
        }
        for (TurnoDTO turno : turnosOdontologo) {
            if (turno.getFecha().equals(turnoDTO.getFecha())) {
                throw new TurnoWithDateAlreadyPersisted();
            }
        }
        // como paso solo el id del paciente y el id del odontologo, debo buscar el objeto completo
        return Optional.of(super.agregar(turnoDTO).get());
    }

    public Set<TurnoDTO> findByPatient(UUID pacienteId) {
        List<Turno> turnos = super.getRepository().findAllByPaciente_id(pacienteId);
        Set<TurnoDTO> turnoDTOS = new HashSet<>();

        for (Turno turno : turnos) {
            turnoDTOS.add(super.getMapper().convertValue(turno, TurnoDTO.class));
        }

        return turnoDTOS;
    }

    public Set<TurnoDTO> findByOdontologo(UUID odontologoID) {
        List<Turno> turnos = super.getRepository().findAllByOdontologo_id(odontologoID);
        Set<TurnoDTO> turnoDTOS = new HashSet<>();

        for (Turno turno : turnos) {
            turnoDTOS.add(super.getMapper().convertValue(turno, TurnoDTO.class));
        }

        return turnoDTOS;
    }
}
