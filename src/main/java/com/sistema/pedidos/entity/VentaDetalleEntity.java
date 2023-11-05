package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "ventasDetalle")
public class VentaDetalleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVentaDetalle;
    @Column
    private Integer cantidad;
    @Column
    private double precioUnitario;
    @Column
    private double precioTotal;
    @OneToOne
    @JoinColumn(name = "producto_id", nullable = true)
    private Product product;


    @OneToOne
    @JoinColumn(name = "venta_id", nullable = false)
    private VentaEntity ventaEntity;

    @OneToOne
    @JoinColumn(name = "plato_id", nullable = true)
    private Plato plato;

}

