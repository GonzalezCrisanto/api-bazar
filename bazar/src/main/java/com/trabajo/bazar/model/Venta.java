package com.trabajo.bazar.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import java.time.LocalDate;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

@Entity @Getter @Setter
public class Venta {
    
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long codigoVenta;
    private LocalDate fechaVenta;
    private Double total;
    private List<Producto> listaProductos;
    @OneToOne
    @JoinColumn(name = "fkIdCliente", referencedColumnName = "idCliente")
    private Cliente cliente;

    public Venta() {
    }

    public Venta(Long codigoVenta, LocalDate fechaVenta, Double total, List<Producto> listaProductos, Cliente cliente) {
        this.codigoVenta = codigoVenta;
        this.fechaVenta = fechaVenta;
        this.total = total;
        this.listaProductos = listaProductos;
        this.cliente = cliente;
    }
    
    
    
}
