package com.trabajo.bazar.service;

import com.trabajo.bazar.exceptions.ClienteNoEncontradoException;
import com.trabajo.bazar.model.Cliente;
import com.trabajo.bazar.repository.IClienteRepository;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClienteService implements IClienteService{
    
    @Autowired
    private IClienteRepository repo;

    @Override
    public void saveCliente(Cliente cliente){
        repo.save(cliente);
    }

    @Override
    public List<Cliente> getClientes(){
        return repo.findAll();
    }

    @Override
    public void deleteCliente(Long id){
        try{
            this.findCliente(id);
            repo.deleteById(id);
        }catch(ClienteNoEncontradoException e){
            e.getMessage("No se encontró el Cliente que se desea borrar");
        }
    }

    @Override
    public Cliente findCliente(Long id) throws ClienteNoEncontradoException{
        Cliente cliente = repo.findById(id).orElse(null);
        
        if(cliente == null){
            throw new ClienteNoEncontradoException("Cliente con id " + id + " no encontrado");
        }
        return cliente;
    }

    @Override
    public void editCliente(Cliente cliente,Long id){
        Cliente aux;
        try {
            aux = this.findCliente(id);
            aux.setDni(cliente.getDni());
            aux.setNombre(cliente.getNombre());
            aux.setApellido(cliente.getApellido());
            this.saveCliente(aux);
        } catch (ClienteNoEncontradoException ex) {
            Logger.getLogger(ClienteService.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
}
