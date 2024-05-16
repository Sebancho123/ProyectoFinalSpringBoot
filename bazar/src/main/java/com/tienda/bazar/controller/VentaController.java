package com.tienda.bazar.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.tienda.bazar.dto.VentaDTO;
import com.tienda.bazar.model.Producto;
import com.tienda.bazar.model.Venta;
import com.tienda.bazar.service.IVentaService;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VentaController {

    //para llamar a lo logica el service para traer los metodos
    @Autowired
    private IVentaService iVenSer;

    //traer todas las ventas
    @GetMapping("/venta/traerlas")
    public List<Venta> getVentas() {
        return iVenSer.getVentas();
    }

    //crear venta y manejamos si los productos estan o no disponibles
    @PostMapping("/venta/crear")
    public String saveVentas(@RequestBody Venta venta) {
        //para enviar mensajito
        String mensaje = "";
        iVenSer.saveVenta(venta);
        
        return "venta correcta";
    }

    //eliminar venta
    @DeleteMapping("venta/eliminar/{codigo_venta}")
    public String deleteVenta(@PathVariable Long codigo_venta) {
        iVenSer.deleteVenta(codigo_venta);
        return "Eliminada correctamente";
    }

    //tarer venta con si especifico codigo
    @GetMapping("/venta/traer/{codigo_venta}")
    public Venta findVenta(@PathVariable Long codigo_venta) {
        return iVenSer.findVenta(codigo_venta);
    }

    //editar con id
    @PutMapping("/venta/editar/{codigo_ventaOriginal}")
    public Venta editarventa(@PathVariable Long codigo_ventaOriginal, @RequestParam(required = false, name = "codigo_vent") Long codigo_ventaNuevo,
            @RequestParam(required = false, name = "fecha_venta") LocalDate nuevaFechaVenta,
            @RequestParam(required = false, name = "ttal") Double nuevoTotal) {

        iVenSer.editarVenta(codigo_ventaOriginal, codigo_ventaNuevo, nuevaFechaVenta, nuevoTotal);

        Venta venta = iVenSer.findVenta(codigo_ventaOriginal);
        return venta;

    }

    //editar sin la id 
    @PutMapping("/venta/editar")
    public Venta editVenta(@RequestBody Venta venta) {
        iVenSer.editVenta(venta);
        return venta;
    }

    //traer lista de productos de una determinada venta
    @GetMapping("/venta/producto/{codigo_venta}")
    public List<Producto> getProducVentas(@PathVariable Long codigo_venta) {
        return iVenSer.getProducVentas(codigo_venta);
    }

    //para traer las ventas de un determiado dia *aqui trae toda la venta como objeto sin su monto total
    @GetMapping("/venta/{fecha_venta}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public List<Venta> getVenUnDia(@PathVariable LocalDate fecha_venta) {
        return iVenSer.getVenUnDia(fecha_venta);
    }

    //lo mismo de arriba pero devuelve un string imformando las ventas de ese dia y el monto total sumando todas los totales de todas las ventas
    @GetMapping("/venta/montototal/{fecha_venta}")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    public String getMnTtal(@PathVariable LocalDate fecha_venta) {
        return iVenSer.getMnTotal(fecha_venta);
    }

    //traer la mayor venta con dto
    @GetMapping("venta/mayor_venta")
    public VentaDTO getMayorVenta() {
        return iVenSer.getMayorVenta();
    }
    

}
