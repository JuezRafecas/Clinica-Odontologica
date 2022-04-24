package com.ctd_backend_final.clinica_odontologica.unittests.configuration;


import com.ctd_backend_final.clinica_odontologica.service.implementation.OdontologoService;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile("unit_test_controller")
@Configuration
@PropertySource("classpath:application-test.properties")
public class UnitTestControllerConfig {
    @Bean
    @Primary
    public OdontologoService odontologoService() {
        return Mockito.mock(OdontologoService.class);
    }
}
