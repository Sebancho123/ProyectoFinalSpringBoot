package com.tienda.bazar.service;

import com.tienda.bazar.dto.VentaDTO;
import com.tienda.bazar.model.Producto;
import com.tienda.bazar.model.Venta;
import com.tienda.bazar.repository.IProductoRepository;
import com.tienda.bazar.repository.IVentaRepository;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService {

    //para llamar a repository para que se comunique con la base de datos y devuelva o inserte datos
    //
    @Autowired
    private IVentaRepository iVenRepo;

    //la uso para hacer la funcionalidad para que cuando crea una venta y escoje un producto el producto quede eleminado si no tiene suficiente cantidad
    @Autowired
    private IProductoRepository iProRepo;

    @Autowired
    private IProductoService iProSer;

    //traemos todas las ventas que hemos creado
    //
    @Override
    public List<Venta> getVentas() {

        List<Venta> listaVentas = iVenRepo.findAll();
        return listaVentas;
    }

    //para dar de alta o crear una nueva venta y verificar si el producto esta disponible y si tiene por ejemplo 6 y hace una compra baja a 5 me costo mucho esto profe xd 
    //y profe no pude hacer lo de si no esta disponible no se por q no me sale solo merma pero cuando el cliente da a ver los productos pues ay ve q no esta disponible xd
    @Override
    public void saveVenta(Venta venta) {

//        List<Producto> listaVentasPro = venta.getListaProductos();
//        for (Producto prod : listaVentasPro) {
//            if (prod.getCant_disponibles() == 0) {
//                this.deleteVenta(venta.getCodigo_venta());
//                break;
//            } else {
        

        List<Producto> listaproductos = iProSer.getProductos();
        //Producto pro = (Producto) venta.getListaProductos();
        List<Producto> listaVentas = venta.getListaProductos();
        
        iVenRepo.save(venta);

        for (Producto produc : listaproductos) {
            for (int i = 0; i < listaVentas.size(); i++) {
                if (listaVentas.get(i).getCodigo_producto().equals(produc.getCodigo_producto())) {

                    Double cantProduc = produc.getCant_disponibles();
                    
                    if (cantProduc == 0) {
                        produc.setNombre("no_disponible");
                        iProSer.editProducto(produc);

                    } else {
                        cantProduc = (cantProduc - 1);
                        produc.setCant_disponibles(cantProduc);
                        iProSer.editProducto(produc);

                        if (cantProduc == 0) {
                            produc.setCant_disponibles(0.0);
                            //aca se cambia el nombre solo editamos par poner un nuveo producto alli para no andar creando otros o simplemente volvemos a poner el nombre anterior
                            //lo malo es q el nombre se pierde y si no se acuerda pailas poreso cada vez que se acabe el producto ay q ponerle el nombre deuna nuevamente si queremos el mismo producto
                            produc.setNombre("no_disponible");
                            iProSer.editProducto(produc);
                        }

                    }
                }
            }

        }

    }

    //para eliminar una venta especificando su codigo de venta
    @Override
    public void deleteVenta(Long codigo_venta) {
        iVenRepo.deleteById(codigo_venta);
    }

    //para encontrar una venta en especifico especificando su codigo de venta 
    @Override
    public Venta findVenta(Long codigo_venta) {
        return iVenRepo.findById(codigo_venta).orElse(null);
    }

    //para editar una venta especificando su codigo este es editar medio feo xd pero aqui no editamos ni el cliente ni los productos
    @Override
    public Venta editarVenta(Long codigoOriginal, Long codigoNuevo, LocalDate nuevaFecha_venta, Double nuevoTotal) {

        Venta venta = this.findVenta(codigoOriginal);

        venta.setCodigo_venta(codigoNuevo);
        venta.setFecha_venta(nuevaFecha_venta);
        venta.setTotal(nuevoTotal);

        this.saveVenta(venta);
        return venta;

    }

    //para editar sin pasar la id traemos la venta completa asi editamos el cliente y sus productos
    @Override
    public Venta editVenta(Venta venta) {
        this.saveVenta(venta);
        return venta;
    }

    //para traer los productos de una determinada venta
    @Override
    public List<Producto> getProducVentas(Long codigo_venta) {

        Venta venta = this.findVenta(codigo_venta);

        List<Producto> listaProducVenta = venta.getListaProductos();

        return listaProducVenta;

    }

    //para ver las ventas completas de un determinado dia sin sumar su monto total
    @Override
    public List<Venta> getVenUnDia(LocalDate fecha_venta) {

        List<Venta> listaVentas = this.getVentas();
        List<Venta> listaFechaVenta = new ArrayList<Venta>();

        for (Venta venta : listaVentas) {
            if (venta.getFecha_venta().equals(fecha_venta)) {
                listaFechaVenta.add(venta);

                int cantVentas = listaFechaVenta.size() + 1;
                Double MontoTotal = venta.getTotal();
                MontoTotal = (MontoTotal + MontoTotal);

            }
        }

        return listaFechaVenta;
    }

    //lo mismo de arriba pero le retornamos un string dicinedo cuantas ventas hiso y su monto total de las ventas de ese dia
    @Override
    public String getMnTotal(LocalDate fecha_venta) {

        List<Venta> listaVentas = this.getVentas();
        List<Venta> listaFechaVenta = new ArrayList<Venta>();
        String mensaje = "";

        for (Venta venta : listaVentas) {
            if (venta.getFecha_venta().equals(fecha_venta)) {

                listaFechaVenta.add(venta);

                int cantVentas = listaFechaVenta.size();
                Double MontoTotal = 0.0;
                for (Venta vent : listaFechaVenta) {

                    MontoTotal = MontoTotal + vent.getTotal();

                }

                mensaje = "la cantidad de ventas es : " + cantVentas + "," + " el monto total es de : " + MontoTotal;
            }
        }

        return mensaje;

    }

    //para obtener la venta con el total mas alto "mayor venta" en DTO con sus atributos de la clase VentaDTO
    @Override
    public VentaDTO getMayorVenta() {

        List<Venta> listaVentas = this.getVentas();
        Venta maxVent = null;
        boolean band = true;

        for (int i = 0; i < listaVentas.size(); i++) {
            if (band == true) {

                maxVent = listaVentas.get(i);
                band = false;

            } else {
                if (listaVentas.get(i).getTotal() > maxVent.getTotal()) {
                    maxVent = listaVentas.get(i);
                }

            }

        }

        VentaDTO ventaDto = new VentaDTO();
        ventaDto.setCodigo_venta(maxVent.getCodigo_venta());
        ventaDto.setTotal(maxVent.getTotal());
        ventaDto.setListaProductos(maxVent.getListaProductos());
        ventaDto.setNombre(maxVent.getUnCliente().getNombre());
        ventaDto.setNombre(maxVent.getUnCliente().getApellido());

        return ventaDto;

    }

}
