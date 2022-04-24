package com.ctd_backend_final.clinica_odontologica.exceptions;

public class TurnoWithDateAlreadyPersisted extends Exception {
    private static final String MESSAGE = "Ya existe un turno con esa fecha";
    public TurnoWithDateAlreadyPersisted() {
        super(MESSAGE);
    }
}
