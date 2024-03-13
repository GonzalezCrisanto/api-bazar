package com.trabajo.bazar.repository;

import com.trabajo.bazar.model.Venta;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IVentaRepository extends JpaRepository <Venta,Long> {
    
}
