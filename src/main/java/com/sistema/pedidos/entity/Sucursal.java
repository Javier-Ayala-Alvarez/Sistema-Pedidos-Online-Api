package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "sucursales")
@Data
public class Sucursal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String nombre;
    @Column
    private String abreviatura;
    @Column
    private String direccion;
    @Column
    private Double altitud;
    @Column
    private Double longitud;
    @Column
    private Double radio;
    @Column
    private boolean estado=true;
    @ManyToOne
    private Company empresa;

    
}
