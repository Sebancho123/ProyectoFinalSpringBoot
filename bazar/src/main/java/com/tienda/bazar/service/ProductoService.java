package com.tienda.bazar.service;

import com.tienda.bazar.model.Producto;
import com.tienda.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService {

    //para llamar a repository para que se comunique con la base de datos y devuelva o inserte datos
    //
    @Autowired
    private IProductoRepository iProRepo;

    //para traer todos los productos
    @Override
    public List<Producto> getProductos() {
        
        List<Producto> listaProductos = iProRepo.findAll();
        return listaProductos;
    }

    //para dar el alta un producto
    @Override
    public void saveProducto(Producto produc) {
        iProRepo.save(produc);
    }

    //para eliminar un producto dependiendo su codigo
    @Override
    public void deleteProducto(Long codigo_produc) {
        iProRepo.deleteById(codigo_produc);
    }

    //para traer un producto en cuestion con su codigo
    @Override
    public Producto findProducto(Long codigo_produc) {
        return iProRepo.findById(codigo_produc).orElse(null);
    }

    //para editar con el codigo
    @Override
    public Producto editarProducto(Long codigoOriginal, Long nuevoCodigo, String nuevoNombre, String nuevaMarca, Double nuevoPrecio, Double nuevaCantDisponible) {
        
        Producto produc = this.findProducto(codigoOriginal);
        
        produc.setCodigo_producto(nuevoCodigo);
        produc.setNombre(nuevoNombre);
        produc.setMarca(nuevaMarca);
        produc.setPrecio(nuevoPrecio);
        produc.setCant_disponibles(nuevaCantDisponible);
        
        this.saveProducto(produc);
        
        return produc;
        
    }

    //editar sin el codigo objeto completo
    @Override
    public Producto editProducto(Producto produc) {
        this.saveProducto(produc);
        return produc;
    }

    //para traer traer los producto que tengan una cantidad menor a 5
    @Override
    public List<Producto> getCantCinMe() {
        
        List<Producto> listaProductos = this.getProductos();
        List<Producto> listaCantProduc = new ArrayList<Producto>();
        
        for (Producto produc : listaProductos) {
            if (produc.getCant_disponibles() < 5) {
                listaCantProduc.add(produc);
            }
        }
        
        return listaCantProduc;
        
    }
    
    @Override
    public List<Producto> getProducNDisponi() {
        
        List<Producto> listaProductos = this.getProductos();
        List<Producto> listaProNoDisponi = new ArrayList<Producto>();
        
        for (Producto producto : listaProductos) {
            if (producto.getNombre().equals("no_disponible")) {
                listaProNoDisponi.add(producto);
            }
        }
        
        return listaProNoDisponi;
    }
    
    @Override
    public List<Producto> getCompuMarca(String marca) {
        List<Producto> listaProCompu = this.getProductos();
        List<Producto> listaCompuMarca = this.getProductos();
        
        for (Producto producto : listaProCompu) {
            if (producto.getMarca().equalsIgnoreCase(marca)) {
                listaCompuMarca.add(producto);
            }
            if (producto.getMarca().equals("costumizada")) {
                listaCompuMarca.add(producto);
            }
        }
        
        return listaCompuMarca;
        
    }
    
}
