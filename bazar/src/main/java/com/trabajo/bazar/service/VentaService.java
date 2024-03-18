package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.model.Venta;
import com.trabajo.bazar.repository.IProductoRepository;
import com.trabajo.bazar.repository.IVentaRepository;
import com.trabajo.bazar.ventaClienteDTO.VentaClienteDTO;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VentaService implements IVentaService{
    
    @Autowired
    private IVentaRepository repoV;
    private IProductoRepository repoP;

    @Override
    public String saveVenta(Venta venta){
        String mensaje = "";
        
        if(this.checkStock(venta)){
            repoV.save(venta);
            mensaje = "La venta se pudo concretar";
        }else{
            mensaje = "La venta no se pudo concretar por falta de stock";
        }
        return mensaje;
    }

    @Override
    public List<Venta> getVentas(){
        return repoV.findAll();
    }

    @Override
    public void deleteVenta(Long id){
        repoV.deleteById(id);
    }

    @Override
    public Venta findVenta(Long id){ 
        return repoV.findById(id).orElse(null);
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
        List<Venta> listaVentas = repoV.findAll();
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
        List<Venta> listaVentas = repoV.findAll();
        VentaClienteDTO ventaMayor = new VentaClienteDTO();
        
        ventaMayor.setCodigoVenta(listaVentas.get(0).getCodigoVenta());
        ventaMayor.setTotal(listaVentas.get(0).getTotal());
        ventaMayor.setApellidoCliente(listaVentas.get(0).getCliente().getApellido());
        ventaMayor.setNombreCliente(listaVentas.get(0).getCliente().getNombre());
        ventaMayor.setCantidadProductos(listaVentas.get(0).getListaProductos().size());
        
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
    
    @Override
    public void discountStock(Map<Long, Integer> cantidadRequerida){
        
        for (Map.Entry<Long, Integer> entry : cantidadRequerida.entrySet()) {
            Long codigoProducto = entry.getKey();
            int cantidadRequeridaProducto = entry.getValue();
            Producto producto = repoP.findById(codigoProducto).orElse(null);
            
            producto.setCantidadDisponible(producto.getCantidadDisponible()-cantidadRequeridaProducto);
            repoP.save(producto);
        }
    }

    @Override
    public Boolean checkStock(Venta venta) {
        Map<Long, Integer> cantidadRequerida = new HashMap<>();
        List<Producto> listaProductos = venta.getListaProductos();
        Boolean indicador = true;
        
        // Contar la cantidad requerida de cada producto en la lista de productos de la venta
        for (Producto producto : listaProductos) {
            if(cantidadRequerida.containsKey(producto.getCodigoProducto())){
                cantidadRequerida.put(producto.getCodigoProducto(), + 1);
            }else{
                cantidadRequerida.put(producto.getCodigoProducto(),1);
            }
        }
        
        // Verificar si hay suficiente stock para cada producto en la lista
        for (Map.Entry<Long, Integer> entry : cantidadRequerida.entrySet()) {
            Long codigoProducto = entry.getKey();
            int cantidadRequeridaProducto = entry.getValue();
            Producto producto = repoP.findById(codigoProducto).orElse(null);
            
            if (producto.getCantidadDisponible() < cantidadRequeridaProducto) {
                indicador = false; // No hay suficiente stock para al menos uno de los productos
            }
        }
        
        if(indicador){
            this.discountStock(cantidadRequerida);
        }
        return indicador; // Hay suficiente stock para todos los productos en la lista
    }

}
