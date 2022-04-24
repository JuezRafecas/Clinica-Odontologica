package com.ctd_backend_final.clinica_odontologica.controller.implementation;

import com.ctd_backend_final.clinica_odontologica.controller.AController;
import com.ctd_backend_final.clinica_odontologica.model.DTO.OdontologoDTO;
import com.ctd_backend_final.clinica_odontologica.service.IService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/odontologos")
@CrossOrigin("http://localhost:3000")
public class OdontologoController extends AController<IService<OdontologoDTO>, OdontologoDTO> { }
