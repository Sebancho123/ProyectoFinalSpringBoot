
package com.tienda.bazar.service;

import com.tienda.bazar.dto.VentaDTO;
import com.tienda.bazar.model.Producto;
import com.tienda.bazar.model.Venta;
import java.time.LocalDate;
import java.util.List;


public interface IVentaService {
 
    //para traer todos
    public List<Venta> getVentas();
    
    //para crear venta nueva
    public void saveVenta(Venta venta);
    
    //para eliminar venta
    public void deleteVenta(Long codigo_venta);
    
    //para encontrar una venta en cuestion
    public Venta findVenta(Long codigo_venta);
    
    //para editar con la id
    public Venta editarVenta(Long codigoOriginal, Long codigoNuevo, LocalDate nuevaFecha_venta, Double nuevoTotal);
    
    //para editar sin la id
    public Venta editVenta(Venta venta);

    //para traer la lista de productos de una determinada venta
    public List<Producto> getProducVentas(Long codigo_venta);   

    //para traer las ventas de un dia *trae todo la venta sin sumar el monto total
    public List<Venta> getVenUnDia(LocalDate fecha_venta);

    //lo mismo de arriba pero lo devuelve en modo string dicineto las ventas de ese dia mas el monto total de todas las ventas sumadas
    public String getMnTotal(LocalDate fecha_venta);

    //traer la venta con mayor total *la mayor venta en dto con los atributos de la clase dto
    public VentaDTO getMayorVenta();
    
}
