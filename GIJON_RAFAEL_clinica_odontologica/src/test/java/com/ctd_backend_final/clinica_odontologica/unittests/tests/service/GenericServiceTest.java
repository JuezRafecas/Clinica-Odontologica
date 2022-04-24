package com.ctd_backend_final.clinica_odontologica.unittests.tests.service;

import com.ctd_backend_final.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Odontologo;
import com.ctd_backend_final.clinica_odontologica.repository.IOdontologoRepository;
import com.ctd_backend_final.clinica_odontologica.service.implementation.OdontologoService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("unit_test_service")
public class GenericServiceTest {
    @Autowired
    private IOdontologoRepository odontologoRepository;
    @Autowired
    private OdontologoService odontologoService;


    @Test
    public void persistirOdontologo() throws TurnoWithDateAlreadyPersisted {
        Odontologo odontologo = new Odontologo();
        UUID uuid = new UUID(0, 0);
        odontologo.setId(uuid);
        Mockito.when(odontologoRepository.save(Mockito.any(Odontologo.class))).thenAnswer(invocation -> {
            Odontologo odontologo1 = invocation.getArgument(0);
            odontologo1.setId(uuid);
            return odontologo1;
        });

        OdontologoDTO odontologoDTO = new OdontologoDTO();
        odontologoDTO.setNombre("nombre");
        odontologoService.agregar(odontologoDTO);

        Mockito.verify(odontologoRepository, Mockito.times(1)).save(Mockito.any(Odontologo.class));
        assert (odontologo.getId().equals(uuid));
        assert (odontologo.getNombre().equals("nombre"));
    }

    @Test
    public void eliminarOdontologo() throws ResourceNotFoundException {

        odontologoService.eliminar(UUID.randomUUID());

        Mockito.verify(odontologoRepository, Mockito.times(1)).deleteById(Mockito.any(UUID.class));
    }

    @Test
    public void buscarOdontologoPorId() {
        Mockito.when(odontologoRepository.findById(Mockito.any(UUID.class))).thenAnswer(invocation -> {
            Odontologo odontologo = new Odontologo();
            odontologo.setId(invocation.getArgument(0));
            return Optional.of(odontologo);
        });

        UUID id = UUID.randomUUID();
        OdontologoDTO odontologo = odontologoService.buscarPorId(id).get();

        Mockito.verify(odontologoRepository, Mockito.times(1)).findById(Mockito.any(UUID.class));
        assert (odontologo.getId().equals(id));
    }

    @Test
    public void actualizarOdontologo() {
        OdontologoDTO odontologoDTO = new OdontologoDTO();

        odontologoService.actualizar(odontologoDTO);

        Mockito.verify(odontologoRepository, Mockito.times(1)).save(Mockito.any(Odontologo.class));
    }

    @Test
    public void buscarTodosOdontologos() {
        Mockito.when(odontologoRepository.findAll()).thenReturn(List.of(new Odontologo(), new Odontologo()));

        Set<OdontologoDTO> odontologos = odontologoService.buscarTodos();

        Mockito.verify(odontologoRepository, Mockito.times(1)).findAll();
        assert (odontologos.size() == 2);
    }




}
