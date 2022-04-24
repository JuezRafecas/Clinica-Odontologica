package com.ctd_backend_final.clinica_odontologica.model;


import java.util.UUID;

public abstract class ADTO {
    // necesario para que al usarlo como clase superior en generics tenga el metodo
    public UUID getId() {return null;};
}
