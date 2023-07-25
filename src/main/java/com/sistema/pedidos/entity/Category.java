package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Categoria")
@Data
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column
    private String CT_Nombre;
    @Column
    private boolean CT_Estado=false;

    @Column(length = 1000)
    private String img;
}
