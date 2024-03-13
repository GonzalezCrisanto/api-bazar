package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Producto;
import com.trabajo.bazar.repository.IProductoRepository;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductoService implements IProductoService{

    @Autowired
    private IProductoRepository repo;
    
    @Override
    public void saveProducto(Producto producto){
        repo.save(producto);
    }

    @Override
    public List<Producto> getProductos(){
        return repo.findAll();
    }

    @Override
    public void deleteProducto(Long id){
        repo.deleteById(id);
    }

    @Override
    public Producto findProducto(Long id){
        return repo.findById(id).orElse(null);
    }

    @Override
    public void editProducto(Producto producto,Long id){
        Producto aux = this.findProducto(id);
        
        aux.setNombre(producto.getNombre());
        aux.setMarca(producto.getMarca());
        aux.setCosto(producto.getCosto());
        aux.setCantidadDisponible(producto.getCantidadDisponible());
        
        this.saveProducto(aux);
    }

    @Override
    public List<Producto> getProductosMenos5(){
        List<Producto> listaProductos = repo.findAll();
        List<Producto> listaNueva = new ArrayList<Producto>();
        
        for(Producto aux : listaProductos){
            if(aux.getCantidadDisponible() < 5){
                listaNueva.add(aux);
            }
        }
        return listaNueva;
    }
    
}
