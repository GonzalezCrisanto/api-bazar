package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Cliente;
import java.util.List;

public interface IClienteService {
    
    public void saveCliente(Cliente cliente);
    
    public List<Cliente> getClientes();
    
    public void deleteCliente(Long id);
    
    public Cliente findCliente(Long id);
    
    public void editCliente(Cliente cliente,Long id);
}
