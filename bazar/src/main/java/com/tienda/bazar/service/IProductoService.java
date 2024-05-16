
package com.tienda.bazar.service;

import com.tienda.bazar.model.Producto;
import java.util.List;


public interface IProductoService {
    
    //para traer todos los productos
    public List<Producto> getProductos();
    
    //para dar de alta un producto
    public void saveProducto(Producto produc);
    
    //para eliminar el producto con su codigo
    public void deleteProducto(Long codigo_produc);
    
    //para traer un producto dependiendo el codigo que le pasemos
    public Producto findProducto(Long codigo_produc);
    
    //para editar con codigo
    public Producto editarProducto(Long codigoOriginal, Long nuevoCodigo, String nuevoNombre, String nuevaMarca, Double nuevoPrecio, Double nuevaCantDisponible);
    
    //para editar sin codigo objeto completo
    public Producto editProducto(Producto produc);

    //para traer todos los productos menor a 5
    public List<Producto> getCantCinMe();
    
    //para traer todos los productos q no esten disponibles * que la cant producto sea 0
    public List<Producto>getProducNDisponi();
    
    //para burcar es como un buscador solo de computadoras poner la marca y salta si hay se puede hacer un buscador global de todo tipo de marcas por ejemplo ollas y la marca o gallinas y en la marca del producto puede poner el color o su raza xd pero es mucho embole y bueno por ahora
    //dejar asi pero se puede ir emplementando poco a poco las cosas perdon por tantisimo texto xd
    public List<Producto>getCompuMarca(String marca);
}
