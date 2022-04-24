package com.ctd_backend_final.clinica_odontologica.config.testing;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;

@Profile("runtime-test")
@Configuration
@PropertySource("classpath:application-test.properties")
public class ConfigurationTest {
}
