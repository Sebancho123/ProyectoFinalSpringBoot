
package com.tienda.bazar.controller;

import com.tienda.bazar.model.Producto;
import com.tienda.bazar.service.IProductoService;
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
public class ProductoController {
    
    //llamar a la logica de negocio*service
    @Autowired
    private IProductoService iProSer;
    
    //para traer todos los productos
    @GetMapping("/producto/traerlos")
    public List<Producto> getProductos() {
        return iProSer.getProductos();
    }
    
    //para dar el alta a un producto
    @PostMapping("/producto/crear")
    public String saveProductos(@RequestBody Producto produc) {
        iProSer.saveProducto(produc);
        return "creado eficientemente";
    }
    
    //para eliminar el producto dependiendo su codigo
    @DeleteMapping("/producto/eliminar/{id_producto}")
    public String deleteProducto(@PathVariable Long codigo_produc){
        iProSer.deleteProducto(codigo_produc);
        return "eliminado correctamente";
    }
    
    //para traer un producto dependiendo el codigo que le pasemos
    @GetMapping("/producto/traer/{id_producto}")
    public Producto findProducto (@PathVariable Long codigo_produc) {
        return iProSer.findProducto(codigo_produc);
    }
    
    //para eitar con su codigo;
    @PutMapping("/producto/editar/{idOriginal}")
    public Producto editarProducto (@PathVariable Long codigo_produc, @RequestParam(required = false, name = "codigo_produc") Long nuevoCodigoProduc,
                                    @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                    @RequestParam(required = false, name = "marca") String nuevaMarca,
                                    @RequestParam(required = false, name = "precio") Double nuevoPrecio,
                                    @RequestParam(required = false, name = "cant_disponibles") Double nuevaCantDispo) {
        
        iProSer.editarProducto(codigo_produc, nuevoCodigoProduc, nuevoNombre, nuevaMarca, nuevoPrecio, nuevaCantDispo);
        
        Producto produc = this.findProducto(nuevoCodigoProduc);
        return produc;
    }
    
    //para editar sin su codigo objeto completo
    @PutMapping("/producto/editar")
    public Producto editProducto(@RequestBody Producto produc) {
        iProSer.editProducto(produc);
        return produc;
    }
    
    //todos los productos que sean menor que 5
    @GetMapping("/producto/faltastock")
    public List<Producto> getCantCinMe() {
        return iProSer.getCantCinMe();
    }
    
    //para traer todos los productos no disponibles ya eliminar o usar con ese mismo id para no andar creando otros
    @GetMapping("/producto/no_disponible")
    public List<Producto>getProducNDisponi() {
        return iProSer.getProducNDisponi();
    }
    
    //para traer conputadoras dependiendo la marca tambien puedes poner personalizada o customizada y te trae unas ensambladas xd y su descripcion
    @GetMapping("/producto/computadora/{marca}") 
    public List<Producto>getCompuMarca (String marca) {
        return iProSer.getCompuMarca(marca);
    }
    
}
