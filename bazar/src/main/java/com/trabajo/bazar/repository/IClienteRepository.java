package com.trabajo.bazar.repository;

import com.trabajo.bazar.model.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;


public interface IClienteRepository extends JpaRepository <Cliente,Long>{
    
}
