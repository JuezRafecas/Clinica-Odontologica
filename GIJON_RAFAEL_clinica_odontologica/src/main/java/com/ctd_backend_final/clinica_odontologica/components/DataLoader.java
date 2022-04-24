package com.ctd_backend_final.clinica_odontologica.components;

import com.ctd_backend_final.clinica_odontologica.model.entity.Domicilio;
import com.ctd_backend_final.clinica_odontologica.model.entity.Odontologo;
import com.ctd_backend_final.clinica_odontologica.model.entity.Paciente;
import com.ctd_backend_final.clinica_odontologica.model.entity.Turno;
import com.ctd_backend_final.clinica_odontologica.repository.IDomicilioRepository;
import com.ctd_backend_final.clinica_odontologica.repository.IOdontologoRepository;
import com.ctd_backend_final.clinica_odontologica.repository.IPacienteRepository;
import com.ctd_backend_final.clinica_odontologica.repository.ITurnoRepository;
import com.ctd_backend_final.clinica_odontologica.security.entity.UserRoles;
import com.ctd_backend_final.clinica_odontologica.security.entity.Usuario;
import com.ctd_backend_final.clinica_odontologica.security.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.UUID;

@Component
@Profile("default")
public class DataLoader implements ApplicationRunner {

    private IUserRepository userRepository;
    private IPacienteRepository pacienteRepository;
    private ITurnoRepository turnoRepository;
    private IOdontologoRepository odontologoRepository;
    private IDomicilioRepository domicilioRepository;

    public static UUID ODONTOLOGO_ID;
    public static UUID PACIENTE_ID;
    public static UUID TURNO_ID;
    public static Date FECHA_TURNO = new Date(122, 12, 30);
    public static UUID DOMICILIO_ID;

    @Autowired
    public void setUserRepository(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }
    @Autowired
    public void setPacienteRepository(IPacienteRepository pacienteRepository) { this.pacienteRepository = pacienteRepository; }
    @Autowired
    public void setTurnoRepository(ITurnoRepository turnoRepository) { this.turnoRepository = turnoRepository; }
    @Autowired
    public void setOdontologoRepository(IOdontologoRepository odontologoRepository) { this.odontologoRepository = odontologoRepository; }
    @Autowired
    public void setDomicilioRepository(IDomicilioRepository domicilioRepository) { this.domicilioRepository = domicilioRepository; }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String password = encoder.encode("admin");
        userRepository.save(Usuario.builder().username("henry").password(password).roles(UserRoles.ROLE_ADMIN).build());
        userRepository.save(Usuario.builder().username("user").password(password).roles(UserRoles.ROLE_USER)/*.paciente(paciente)*/.build());

        //En caso de que se desee tener datos precargados!
//        Paciente paciente = new Paciente();
//        paciente.setNombre("Pascualino");
//        paciente.setApellido("Del Pesco");
//        paciente.setId(PACIENTE_ID);
//        paciente.setDni("984982398");
//        HashSet<Domicilio> domicilios = new HashSet<>();
//        paciente.setDomicilios(domicilios);
//        PACIENTE_ID = pacienteRepository.save(paciente).getId();
//
//        Odontologo odontologo = new Odontologo();
//        odontologo.setNombre("Jorge");
//        odontologo.setApellido("Muelas");
//        odontologo.setMatricula(13949);
//        odontologo.setId(ODONTOLOGO_ID);
//        ODONTOLOGO_ID = odontologoRepository.save(odontologo).getId();
//
//        Turno turno = new Turno();
//        turno.setId(TURNO_ID);
//        turno.setPaciente(paciente);
//        turno.setOdontologo(odontologo);
//        turno.setFecha(FECHA_TURNO);
//        TURNO_ID = turnoRepository.save(turno).getId();
//
//        Domicilio domicilio = new Domicilio();
//        domicilio.setId(DOMICILIO_ID);
//        domicilio.setCalle("San Martin");
//        domicilio.setNumero("322");
//        domicilio.setLocalidad("Caballito");
//        domicilio.setProvincia("Buenos Aires");
//        domicilio.setPaciente(paciente);
//        DOMICILIO_ID = domicilioRepository.save(domicilio).getId();
    }


}
