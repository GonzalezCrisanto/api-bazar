package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.model.Venta;
import com.trabajo.bazar.repository.IVentaRepository;
import com.trabajo.bazar.ventaClienteDTO.VentaClienteDTO;
import java.time.LocalDate;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    private IVentaRepository repo;

    @Override
    public void saveVenta(Venta venta){
        repo.save(venta);
    }

    @Override
    public List<Venta> getVentas(){
        return repo.findAll();
    }

    @Override
    public void deleteVenta(Long id){
        repo.deleteById(id);
    }

    @Override
    public Venta findVenta(Long id){ 
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editVenta(Venta venta,Long id){
        Venta aux = this.findVenta(id);
        
        aux.setCliente(venta.getCliente());
        aux.setListaProductos(venta.getListaProductos());
        aux.setTotal(venta.getTotal());
        aux.setCodigoVenta(venta.getCodigoVenta());
        aux.setFechaVenta(venta.getFechaVenta());
        
        this.saveVenta(aux);
    }

    @Override
    public List<Producto> getProductosXventa(Long id) {
        return this.findVenta(id).getListaProductos();
    }

    @Override
    public String getVentaxDia(LocalDate fecha){
        List<Venta> listaVentas = repo.findAll();
        Double montoTotal = 0.0;
        int venta = 0;
        
        for(Venta aux : listaVentas){
            if(aux.getFechaVenta().equals(fecha)){
                montoTotal += aux.getTotal();
                venta++;
            }
        }
        return "El monto total es de: " + montoTotal + " y la cantidad de ventas realizadas es de: " + venta;
    }

    @Override
    public VentaClienteDTO getMayorVenta(){
        List<Venta> listaVentas = repo.findAll();
        VentaClienteDTO ventaMayor = new VentaClienteDTO();
        
        ventaMayor.setCodigoVenta(listaVentas.getFirst().getCodigoVenta());
        ventaMayor.setTotal(listaVentas.getFirst().getTotal());
        ventaMayor.setApellidoCliente(listaVentas.getFirst().getCliente().getApellido());
        ventaMayor.setNombreCliente(listaVentas.getFirst().getCliente().getNombre());
        ventaMayor.setCantidadProductos(listaVentas.getFirst().getListaProductos().size());
        
        for(Venta aux : listaVentas){
            if(aux.getTotal() > ventaMayor.getTotal()){
                ventaMayor.setCodigoVenta(aux.getCodigoVenta());
                ventaMayor.setTotal(aux.getTotal());
                ventaMayor.setApellidoCliente(aux.getCliente().getApellido());
                ventaMayor.setNombreCliente(aux.getCliente().getNombre());
                ventaMayor.setCantidadProductos(aux.getListaProductos().size());
            }
        }
        return ventaMayor;
    }
    
    
}