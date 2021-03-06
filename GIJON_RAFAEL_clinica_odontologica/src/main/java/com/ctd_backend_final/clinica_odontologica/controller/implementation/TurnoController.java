package com.ctd_backend_final.clinica_odontologica.controller.implementation;

import com.ctd_backend_final.clinica_odontologica.controller.AController;
import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import com.ctd_backend_final.clinica_odontologica.model.DTO.TurnoDTO;
import com.ctd_backend_final.clinica_odontologica.service.IService;
import com.ctd_backend_final.clinica_odontologica.service.implementation.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@RequestMapping("/turnos")

public class TurnoController extends AController<IService<TurnoDTO>, TurnoDTO> {
    @Override
    @PostMapping
    public ResponseEntity<Optional<TurnoDTO>> crear(@RequestBody TurnoDTO turnoDTO){
        try {
            return super.crear(turnoDTO);
        } catch (TurnoWithDateAlreadyPersisted turnoWithDateAlreadyPersisted) {
            return ResponseEntity.badRequest().body(Optional.empty());
        }
    }


    @GetMapping("/paciente/{id}")

    public ResponseEntity<Set<TurnoDTO>> buscarPorPaciente(@PathVariable UUID id) {
        TurnoService service = (TurnoService) super.getService();
        return ResponseEntity.ok(service.findByPatient(id));
    }

    @GetMapping("/odontologo/{id}")
    public ResponseEntity<Set<TurnoDTO>> buscarPorOdontologo(@PathVariable UUID id) {
        TurnoService service = (TurnoService) super.getService();
        return ResponseEntity.ok(service.findByOdontologo(id));
    }
}
