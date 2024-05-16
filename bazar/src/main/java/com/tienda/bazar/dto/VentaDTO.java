
package com.tienda.bazar.dto;

import com.tienda.bazar.model.Producto;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class VentaDTO {
    
    private Long codigo_venta;
    private Double total;
    private List<Producto> listaProductos;
    private String nombre;
    private String apellido;

    public VentaDTO() {
    }

    public VentaDTO(Long codigo_venta, Double total, List<Producto> listaProductos, String nombre, String apellido) {
        this.codigo_venta = codigo_venta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.nombre = nombre;
        this.apellido = apellido;
    }
    
}
