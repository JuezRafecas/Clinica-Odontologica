package com.ctd_backend_final.clinica_odontologica.controller.implementation;

import com.ctd_backend_final.clinica_odontologica.controller.AController;
import com.ctd_backend_final.clinica_odontologica.model.DTO.DomicilioDTO;
import com.ctd_backend_final.clinica_odontologica.model.DTO.PacienteDTO;
import com.ctd_backend_final.clinica_odontologica.service.IService;
import com.ctd_backend_final.clinica_odontologica.service.implementation.DomicilioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.UUID;


@RestController
@RequestMapping("/pacientes")
public class PacienteController extends AController<IService<PacienteDTO>, PacienteDTO> {
    @Autowired
    private DomicilioService domicilioService;

    @PostMapping("/{id}/domicilio")
    public ResponseEntity<Optional<DomicilioDTO>> addDomicilio(@PathVariable("id") UUID id, @RequestBody DomicilioDTO domicilioDTO) {
        domicilioDTO.setPaciente(super.getService().buscarPorId(id).get());
        return Optional.ofNullable(domicilioService.guardar(domicilioDTO))
                .map(_dto -> ResponseEntity.ok().body(_dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}/domicilio")
    public ResponseEntity<?> updateDomicilio(@PathVariable("id") UUID id, @RequestBody DomicilioDTO domicilioDTO) {
        domicilioDTO.setPaciente(super.getService().buscarPorId(id).get());
        domicilioService.guardar(domicilioDTO);
        return ResponseEntity.ok().build();
    }
}