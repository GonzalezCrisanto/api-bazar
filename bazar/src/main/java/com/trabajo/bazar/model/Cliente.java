package com.trabajo.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String dni;
}
