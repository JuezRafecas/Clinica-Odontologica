package com.ctd_backend_final.clinica_odontologica.service.implementation;

import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.ctd_backend_final.clinica_odontologica.model.entity.Odontologo;
import com.ctd_backend_final.clinica_odontologica.repository.IOdontologoRepository;
import com.ctd_backend_final.clinica_odontologica.service.AService;
import org.springframework.stereotype.Service;

@Service
public class OdontologoService extends AService<Odontologo, OdontologoDTO, IOdontologoRepository> {
    public OdontologoService() {
        super.setDtoClazz(OdontologoDTO.class);
        super.setEntityClazz(Odontologo.class);
    }
}
