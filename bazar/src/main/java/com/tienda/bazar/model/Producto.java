
package com.tienda.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
@Entity
public class Producto {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_producto;
    private String nombre;
    private String marca;
    //lo veo muy necesario en un producto profe
    private String descripcion;
    private Double precio;
    private Double cant_disponibles;

    public Producto() {
    }

    public Producto(Long codigo_producto, String nombre, String marca, Double precio, Double cant_disponibles) {
        this.codigo_producto = codigo_producto;
        this.nombre = nombre;
        this.marca = marca;
        this.precio = precio;
        this.cant_disponibles = cant_disponibles;
    }
    
}
