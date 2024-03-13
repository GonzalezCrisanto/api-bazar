package com.trabajo.bazar.controller;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.service.IProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductoController {
    
    @Autowired
    private IProductoService serv;
    
    //Crear Producto
    @PostMapping("/productos/crear")
    public String saveProducto(@RequestBody Producto producto){
        serv.saveProducto(producto);
        return "El producto se ha guardado correctamente";
    }
    
    //Traer Productos
    @GetMapping("/productos")
    public List<Producto> getProductos(){
        return serv.getProductos();
    }
    
    //Buscar Producto
    @GetMapping("/productos/{id}")
    public Producto findProducto(@PathVariable Long id){
        return serv.findProducto(id);
    }
    
    //Borrar Producto
    @DeleteMapping("/productos/eliminar/{id}")
    public String deleteProducto(@PathVariable Long id){
        serv.deleteProducto(id);
        return "El producto se ha borrado correctamente";
    }
    
    //Traer Productos con faltante de stock
    @GetMapping("/productos/falta_stock")
    public List<Producto> getFaltanteStock(){
       return serv.getProductosMenos5();
    }
    
}
