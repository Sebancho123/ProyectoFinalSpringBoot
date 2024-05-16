
package com.tienda.bazar.controller;

import com.tienda.bazar.model.Cliente;
import com.tienda.bazar.service.IClienteService;
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
public class ClienteController {
    
    //llamar a lo logica de negocio
    @Autowired
    private IClienteService iCliServ;
    
    //para traer todos los clientes
    @GetMapping("/cliente/traerlos")
    public List<Cliente> getClientes() {
        return iCliServ.getClientes();
    }
    
    //para dar de alta un cliente
    @PostMapping("/cliente/crear")
    public String saveClientes(@RequestBody Cliente cliente){
        iCliServ.saveCliente(cliente);
        return "cliente creado correctamente";
    }
    
    //para eliminar un cliente
    @DeleteMapping("/cliente/eliminar/{id_cliente}")
    public String deleteCliente(@PathVariable Long id_cliente){
        iCliServ.deleteCliente(id_cliente);
        return "Eliminado correctamente";
    }
    
    //para traer el cliente en cuestion con su id
    @GetMapping("/cliente/traer/{id_cliente}")
    public Cliente findCliente(@PathVariable Long id_cliente){
        return iCliServ.findCliente(id_cliente);
    }
    
    //para editar con la id
    @PutMapping("/cliente/editar/{idOriginal}")
    public Cliente editarCliente(@PathVariable Long idOriginal, @RequestParam(required = false, name = "id_cliente") Long idNueva,
                                 @RequestParam(required = false, name = "dni") String nuevoDni,
                                 @RequestParam(required = false, name = "nombre") String nuevoNombre,
                                 @RequestParam(required = false, name = "apellido") String nuevooApellido){
        
        iCliServ.editarCliente(idOriginal, idNueva, nuevoDni, nuevoNombre, nuevooApellido);
        
        Cliente cliente = this.findCliente(idNueva);
        return cliente;
    }
    
    //para editar el objeto completo *mejor para mi xd
    @PutMapping("/cliente/editar")
    public Cliente editCliente(@RequestBody Cliente cliente) {
        
        iCliServ.editCliente(cliente);
        return iCliServ.findCliente(cliente.getId_cliente());
        
    }
    
}
