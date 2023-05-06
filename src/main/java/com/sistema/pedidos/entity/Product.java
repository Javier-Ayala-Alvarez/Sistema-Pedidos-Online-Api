package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "producto")
@Data
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private long id;
    @Column
    private  String nombre;
    @Column
    private  String descripcion;
    @Column
    private  boolean estado;
    @Column
    private Double precioVenta;
    @Column
    private  Double ganancia;
    @Column
    private  String urlImagen;
    @ManyToOne
    private Category category;
    @ManyToOne
    private Evento evento;
    @ManyToOne
    private  Promocion promocion;

}
