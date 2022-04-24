package com.ctd_backend_final.clinica_odontologica.unittests.configuration;


import com.ctd_backend_final.clinica_odontologica.repository.IDomicilioRepository;
import com.ctd_backend_final.clinica_odontologica.repository.IOdontologoRepository;
import com.ctd_backend_final.clinica_odontologica.repository.IPacienteRepository;
import com.ctd_backend_final.clinica_odontologica.repository.ITurnoRepository;
import org.mockito.Mockito;
import org.springframework.context.annotation.*;

@Profile("unit_test_service")
@Configuration
@PropertySource("classpath:application-test.properties")
public class UnitTestServiceConfig {
    @Bean
    @Primary
    public IPacienteRepository pacienteRepository() {
        return Mockito.mock(IPacienteRepository.class);
    }

    @Bean
    @Primary
    public IOdontologoRepository odontologoRepository() {
        return Mockito.mock(IOdontologoRepository.class);
    }

    @Bean
    @Primary
    public ITurnoRepository turnoRepository() {
        return Mockito.mock(ITurnoRepository.class);
    }

    @Bean
    @Primary
    public IDomicilioRepository domicilioRepository() { return Mockito.mock(IDomicilioRepository.class); }
}
