package com.trabajo.bazar.controller;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.model.Venta;
import com.trabajo.bazar.service.IVentaService;
import com.trabajo.bazar.ventaClienteDTO.VentaClienteDTO;
import java.time.LocalDate;
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
public class VentaController {
    
    @Autowired
    private IVentaService serv;
    
    //Crear Venta
    @PostMapping("/ventas/crear")
    public String saveVenta(@RequestBody Venta venta){
        String  mensaje = serv.saveVenta(venta);
        return mensaje;
    }
    
    //Traer todas las ventas
    @GetMapping("/ventas")
    public List<Venta> getVentas(){
        return serv.getVentas();
    }
    
    //Buscar Venta
    @GetMapping("/ventas/{id}")
    public Venta findVenta(@PathVariable Long id){
        return serv.findVenta(id);
    }
    
    //Borrar Venta
    @DeleteMapping("/ventas/eliminar/{id}")
    public String deleteVenta(@PathVariable Long id){
        serv.deleteVenta(id);
        return "La venta se ha borrado correctamente";
    }
    
    //Editar Venta
    @PutMapping("/ventas/editar/{id}")
    public String editVenta(@PathVariable Long id,@RequestBody Venta venta){
        serv.editVenta(venta, id);
        return "La venta se ha actualizado corretamente";
    }
    
    //Productos de una Venta
    @GetMapping("/ventas/productos/{id}")
    public List<Producto> getProductosXventa(@PathVariable Long id){
        return serv.getProductosXventa(id);
    }
    
    //Obtener total y cantidad de ventas en una fecha
    @GetMapping("/ventas/{fecha_venta}")
    public String getTotalyVentas(@PathVariable LocalDate fecha){
        return serv.getVentaxDia(fecha);
    }
    
    //Obtener mayor venta con su respectivo cliente
    @GetMapping("/ventas/mayor_venta")
    public VentaClienteDTO getMayorVenta(){
        return serv.getMayorVenta();
    }
}
