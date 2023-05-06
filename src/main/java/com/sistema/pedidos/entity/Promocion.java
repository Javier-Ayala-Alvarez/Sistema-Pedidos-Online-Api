package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "Promocion")
@Data
public class Promocion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Promocion")
    private  long id;
    @Column(name = "PR_Nombre")
    private  String pr_Nombre;
    @Column(name = "PR_Cantidad")
    private int pr_Cantidad;
    @Column(name = "PR_Porcentaje")
    private double pr_Porcentaje;
}
