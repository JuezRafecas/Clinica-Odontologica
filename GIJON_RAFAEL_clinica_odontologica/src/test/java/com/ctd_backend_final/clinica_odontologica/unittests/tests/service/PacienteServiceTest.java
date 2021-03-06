package com.ctd_backend_final.clinica_odontologica.unittests.tests.service;

import com.ctd_backend_final.clinica_odontologica.model.DTO.DomicilioDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.PacienteDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Paciente;
import com.ctd_backend_final.clinica_odontologica.repository.IPacienteRepository;
import com.ctd_backend_final.clinica_odontologica.service.implementation.PacienteService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.*;


@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("unit_test_service")
public class PacienteServiceTest {
    @Autowired
    PacienteService pacienteService;
    @Autowired
    IPacienteRepository pacienteRepository;

    @Test
    public void aĆ±adirNuevoPaciente() {
        UUID id = new UUID(0,0);

        Mockito.when(pacienteRepository.save(Mockito.any(Paciente.class))).thenAnswer(invocation -> {
            Paciente paciente = invocation.getArgument(0);
            paciente.setId(id);
            return paciente;
        });

        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Juan");
        Optional<PacienteDTO> pacienteDTO1 = pacienteService.agregar(pacienteDTO);

        Mockito.verify(pacienteRepository, Mockito.times(1)).save(Mockito.any(Paciente.class));
        assert(pacienteDTO1.get().getId().equals(id));
    }

    @Test
    public void guardarPacienteInsertarPacienteEnDomicilioTest() {
        PacienteDTO pacienteDTO = new PacienteDTO();
        pacienteDTO.setNombre("Juan");
        Set<DomicilioDTO> domicilios = new HashSet<>();
        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCalle("Calle");
        pacienteDTO.setDomicilios(domicilios);

        Mockito.when(pacienteRepository.save(Mockito.any(Paciente.class))).thenAnswer(invocation -> {
            Paciente paciente = invocation.getArgument(0);
            return paciente;
        });

        PacienteDTO paciente = pacienteService.agregar(pacienteDTO).get();

        for (DomicilioDTO domicilio : paciente.getDomicilios()) {
            assert(domicilio.getPaciente().equals(paciente));
        }
    }
}
