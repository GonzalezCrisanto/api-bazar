package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Cliente;
import com.trabajo.bazar.repository.IClienteRepository;
import java.util.List;
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
        repo.deleteById(id);
    }

    @Override
    public Cliente findCliente(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editCliente(Cliente cliente,Long id){
        Cliente aux = this.findCliente(id);
        
        aux.setDni(cliente.getDni());
        aux.setNombre(cliente.getNombre());
        aux.setApellido(cliente.getApellido());
        
        this.saveCliente(aux);
    }
    
    
}
