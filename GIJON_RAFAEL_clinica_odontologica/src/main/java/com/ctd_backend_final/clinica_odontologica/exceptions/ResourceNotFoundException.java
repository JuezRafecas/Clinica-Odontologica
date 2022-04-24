package com.ctd_backend_final.clinica_odontologica.exceptions;
public class ResourceNotFoundException extends Exception{
    private static final String MESSAGE = "ID not found";
    public ResourceNotFoundException() {
        super(MESSAGE);
    }
}
