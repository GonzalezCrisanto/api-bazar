package com.trabajo.bazar.service;

import com.trabajo.bazar.model.Producto;
import java.util.List;


public interface IProductoService{
    
   public void saveProducto(Producto producto);
    
    public List<Producto> getProductos();
    
    public void deleteProducto(Long id);
    
    public Producto findProducto(Long id);
    
    public void editProducto(Producto producto,Long id);
    
    public List<Producto> getProductosMenos5 ();
}
