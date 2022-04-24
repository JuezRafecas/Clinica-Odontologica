package com.ctd_backend_final.clinica_odontologica.integrationtests.configuration;


import org.springframework.context.annotation.*;

@Configuration
@PropertySource("classpath:application-test.properties")
@Profile("integration_test")
public class IntegrationTestConfig {

}
