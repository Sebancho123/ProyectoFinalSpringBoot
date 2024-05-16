
package com.tienda.bazar.service;

import com.tienda.bazar.model.Cliente;
import com.tienda.bazar.repository.IClienteRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    //para llamar a repository para que se comunique con la base de datos y devuelva o inserte datos
    //
    @Autowired
    private IClienteRepository iCliRepo;

    //para traer todos los clientes guardos
    @Override
    public List<Cliente> getClientes() {
        
        List<Cliente> listaClientes = iCliRepo.findAll();
        return listaClientes;
    }

    //para crear un cliente
    @Override
    public void saveCliente(Cliente cliente) {
        iCliRepo.save(cliente);
    }

    //para eliminar un cliente pasando su id
    @Override
    public void deleteCliente(Long id_cliente) {
        iCliRepo.deleteById(id_cliente);
    }

     //para encontrar un cliente en cuestios
    @Override
    public Cliente findCliente(Long id_cliente) {
        return iCliRepo.findById(id_cliente).orElse(null);
    }

    //para editar un cliente con la id
    @Override
    public Cliente editarCliente(Long idOriginal, Long idNueva, String nuevoDni, String nuevoNombre, String nuevoApellido) {
        
        Cliente cliente = this.findCliente(idOriginal);
        
        cliente.setId_cliente(idNueva);
        cliente.setDni(nuevoDni);
        cliente.setNombre(nuevoNombre);
        cliente.setApellido(nuevoApellido);
        
        this.saveCliente(cliente);
        
        return cliente;
        
    }

    //editar cliente traindo el objeto completo
    @Override
    public Cliente editCliente(Cliente cliente) {
        this.saveCliente(cliente);
        return cliente;
    }
    
}
