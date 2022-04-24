package com.ctd_backend_final.clinica_odontologica.unittests.tests.service;

import com.ctd_backend_final.clinica_odontologica.model.DTO.DomicilioDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Domicilio;
import com.ctd_backend_final.clinica_odontologica.repository.IDomicilioRepository;
import com.ctd_backend_final.clinica_odontologica.service.implementation.DomicilioService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.UUID;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK)
@ActiveProfiles("unit_test_service")
public class DomicilioServiceTest {
    @Autowired
    private IDomicilioRepository domicilioRepository;
    @Autowired
    private DomicilioService domicilioService;


    @Test
    public void persistirDomicilio(){
        UUID id = UUID.randomUUID();
        Mockito.when(domicilioRepository.save(Mockito.any(Domicilio.class))).thenAnswer(invocation -> {
            Domicilio domicilioArg = invocation.getArgument(0);
            domicilioArg.setId(id);
            return domicilioArg;
        });

        DomicilioDTO domicilioDTO = new DomicilioDTO();
        domicilioDTO.setCalle("calle");
        DomicilioDTO response = domicilioService.guardar(domicilioDTO).get();

        assert response.getId().equals(id);
        assert response.getCalle().equals("calle");
    }
}
