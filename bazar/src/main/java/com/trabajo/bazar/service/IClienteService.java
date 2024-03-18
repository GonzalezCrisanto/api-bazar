package com.trabajo.bazar.service;

import com.trabajo.bazar.exceptions.ClienteNoEncontradoException;
import com.trabajo.bazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    public void saveCliente(Cliente cliente);
    
    public List<Cliente> getClientes();
    
    public void deleteCliente(Long id);
    
    public Cliente findCliente(Long id)throws ClienteNoEncontradoException;
    
    public void editCliente(Cliente cliente,Long id);
}
