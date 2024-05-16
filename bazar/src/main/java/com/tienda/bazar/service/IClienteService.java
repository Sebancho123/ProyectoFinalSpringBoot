
package com.tienda.bazar.service;

import com.tienda.bazar.model.Cliente;
import java.util.List;


public interface IClienteService {
    
    //para taer todos los clientes
    public List<Cliente> getClientes();
    
    //para crear un cliente
    public void saveCliente(Cliente cliente);
    
    //para eliminar un cliente con una id
    public void deleteCliente(Long id_cliente);
    
    //para encontar un cliente especificando su id
    public Cliente findCliente(Long id_cliente);
    
    //pare edirar con la id
    public Cliente editarCliente(Long idOriginal, Long idNueva, String nuevoDni, String nuevoNombre, String nuevoApellido);
    
    //para editar sin la id
    public Cliente editCliente(Cliente cliente);
    
}
