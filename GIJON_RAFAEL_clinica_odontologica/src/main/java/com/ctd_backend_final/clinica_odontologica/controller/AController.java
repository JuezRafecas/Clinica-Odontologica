package com.ctd_backend_final.clinica_odontologica.controller;

import com.ctd_backend_final.clinica_odontologica.exceptions.ResourceNotFoundException;
import com.ctd_backend_final.clinica_odontologica.exceptions.TurnoWithDateAlreadyPersisted;
import com.ctd_backend_final.clinica_odontologica.model.ADTO;
import com.ctd_backend_final.clinica_odontologica.service.IService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@RestController
@CrossOrigin("http://localhost:3000")
@Getter
public abstract class AController<Service extends IService<DTO>, DTO extends ADTO> {

    private Service service;

    @Autowired
    public void setService(Service service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<Set<DTO>> buscarTodos() {
        return ResponseEntity.ok(service.buscarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<DTO>> buscarPorId(@PathVariable UUID id) {
        return Optional.ofNullable(service.buscarPorId(id))
                .map(dto -> ResponseEntity.ok().body(dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Optional<DTO>> crear(@RequestBody DTO dto) throws TurnoWithDateAlreadyPersisted {
        if (dto.getId() != null) {
            return ResponseEntity.badRequest().build();
        }
        return Optional.ofNullable(service.agregar(dto))
                .map(_dto -> ResponseEntity.ok().body(_dto))
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping
    public ResponseEntity<?> actualizar(@RequestBody DTO dto) {
        if (dto.getId() == null) {
            return ResponseEntity.badRequest().build();
        }
        service.actualizar(dto);
        return ResponseEntity.ok(HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> borrar(@PathVariable UUID id) throws ResourceNotFoundException {
        if (service.buscarPorId(id).isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        service.eliminar(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }
}
