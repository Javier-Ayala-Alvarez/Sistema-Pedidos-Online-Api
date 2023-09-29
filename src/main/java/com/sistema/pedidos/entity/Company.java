package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "Company")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  Long id;

    @Column
    private  String cp_Nombre;

    @Column
    private  String cp_Direccion;

    @Column
    private  String cp_abreviatura;
    @ManyToOne
    private Evento evento;
}
