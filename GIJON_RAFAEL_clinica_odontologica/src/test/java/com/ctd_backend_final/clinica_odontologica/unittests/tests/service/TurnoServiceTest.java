package com.ctd_backend_final.clinica_odontologica.unittests.tests.service;

import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.PacienteDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.TurnoDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Turno;
import com.ctd_backend_final.clinica_odontologica.repository.ITurnoRepository;
import com.ctd_backend_final.clinica_odontologica.service.implementation.TurnoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("unit_test_service")
public class TurnoServiceTest {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private ITurnoRepository turnoRepository;


    @Test
    public void persistirTurnoConFechaIgualAYaGuardada() {
        Exception exception = null;

        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(new Date(1, 1, 1));
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(UUID.randomUUID());
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(UUID.randomUUID());
        turnoDTO.setPaciente(pacienteDTO);
        turnoDTO.setOdontologo(odontologoDTO);
        Turno turno = new Turno();
        turno.setFecha(new Date(1, 1, 1));

        Mockito.when(turnoRepository.findAllByPaciente_id(Mockito.any(UUID.class))).thenReturn(List.of(turno));
        try {
            turnoService.agregar(turnoDTO);
        } catch (TurnoWithDateAlreadyPersisted e) {
            exception = e;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assert exception != null;
    }

    @Test
    public void persistirTurno() throws TurnoWithDateAlreadyPersisted {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setFecha(new Date(1, 1, 1));
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setId(UUID.randomUUID());
        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setId(UUID.randomUUID());
        turnoDTO.setPaciente(pacienteDTO);
        turnoDTO.setOdontologo(odontologoDTO);

        Mockito.when(turnoRepository.findAllByPaciente_id(Mockito.any(UUID.class))).thenReturn(List.of());
        Mockito.when(turnoRepository.findAllByOdontologo_id(Mockito.any(UUID.class))).thenReturn(List.of());
        Mockito.when(turnoRepository.save(Mockito.any(Turno.class))).thenAnswer(invocation -> invocation.getArgument(0));

        TurnoDTO turno = turnoService.agregar(turnoDTO).get();

        assert turno != null;
        assert turno.getFecha().equals(new Date(1, 1, 1));

        Mockito.verify(turnoRepository, Mockito.times(1)).save(Mockito.any(Turno.class));
        Mockito.verify(turnoRepository, Mockito.times(1)).findAllByPaciente_id(Mockito.any(UUID.class));
        Mockito.verify(turnoRepository, Mockito.times(1)).findAllByOdontologo_id(Mockito.any(UUID.class));
    }

    @Test
    public void buscarTurnoPorIdPaciente() {
        Mockito.clearInvocations(turnoRepository);
        UUID idPaciente = UUID.randomUUID();
        Mockito.when(turnoRepository.findAllByPaciente_id(Mockito.any(UUID.class))).thenReturn(List.of());

        Set<TurnoDTO> turnos = turnoService.findByPatient(idPaciente);

        assert turnos != null;
        assert turnos.isEmpty();

        Mockito.verify(turnoRepository, Mockito.times(1)).findAllByPaciente_id(Mockito.any(UUID.class));

    }

    @Test
    public void buscarTurnoPorIdOdontologo() {
        Mockito.clearInvocations(turnoRepository);
        UUID idOdontologo = UUID.randomUUID();
        Mockito.when(turnoRepository.findAllByOdontologo_id(Mockito.any(UUID.class))).thenReturn(List.of());

        Set<TurnoDTO> turnos = turnoService.findByOdontologo(idOdontologo);

        assert turnos != null;
        assert turnos.isEmpty();

        Mockito.verify(turnoRepository, Mockito.times(1)).findAllByOdontologo_id(Mockito.any(UUID.class));
    }



}
