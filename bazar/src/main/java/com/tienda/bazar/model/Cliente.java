
package com.tienda.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Cliente {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id_cliente;
    private String dni;
    private String nombre;
    private String apellido;

    public Cliente() {
    }

    public Cliente(Long id_cliente, String dni, String nombre, String apellido) {
        this.id_cliente = id_cliente;
        this.dni = dni;
        this.nombre = nombre;
        this.apellido = apellido;
    }
}
