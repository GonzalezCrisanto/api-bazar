package com.trabajo.bazar.repository;

import com.trabajo.bazar.model.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IProductoRepository extends JpaRepository <Producto,Long>{
    
}
