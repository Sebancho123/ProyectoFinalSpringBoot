
package com.tienda.bazar.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;

import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Getter @Setter
@Entity
public class Venta {     
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigo_venta;
    //para poner formato cuando envio una response cuando envi get y me lo muestra con este format;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd/MM/yyyy")  
    private LocalDate fecha_venta;
    private Double total;
    @OneToMany
    private List<Producto> listaProductos;
    
    @OneToOne
    private Cliente unCliente;
    
}
