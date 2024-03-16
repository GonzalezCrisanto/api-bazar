package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.model.Venta;
import com.trabajo.bazar.ventaClienteDTO.VentaClienteDTO;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;


public interface IVentaService {
    
    public String saveVenta(Venta venta);
    
    public List<Venta> getVentas();
    
    public void deleteVenta(Long id);
    
    public Venta findVenta(Long id);
    
    public void editVenta(Venta venta,Long id);
    
    public List<Producto> getProductosXventa(Long id);
    
    public String getVentaxDia(LocalDate fecha);
    
    public VentaClienteDTO getMayorVenta();
    
    public void discountStock(Map<Long, Integer> cantidadRequerida);
    
    public Boolean checkStock(Venta venta);
}
