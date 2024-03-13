package com.trabajo.bazar.controller;

import com.trabajo.bazar.model.Cliente;
import com.trabajo.bazar.service.IClienteService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ClienteController {
    
    @Autowired
    private IClienteService serv;
    
    //Crear Cliente
    @PostMapping("/clientes/crear")
    public String saveCliente(@RequestBody Cliente cliente){
        serv.saveCliente(cliente);
        return "El cliente se ha guardado correctamente";
    }
    
    //Traer clientes
    @GetMapping("/clientes")
    public List<Cliente> getClientes(){
        return serv.getClientes();
    }
    
    //Buscar cliente
    @GetMapping("/clientes/{id}")
    public Cliente findCliente(@PathVariable Long id){
        return serv.findCliente(id);
    }
    
    //Borrar cliente
    @DeleteMapping("/clientes/{id}")
    public String deleteCliente(@PathVariable Long id){
        serv.deleteCliente(id);
        return "El cliente se ha borrado correctamente";
    }
    
    //Editar cliente
    
    @PutMapping("/clientes/{id}")
    public String editCliente(@PathVariable Long id,@RequestBody Cliente cliente){
        serv.editCliente(cliente, id);
        return "El cliente se ha actualizado correctamente";
    }
}
