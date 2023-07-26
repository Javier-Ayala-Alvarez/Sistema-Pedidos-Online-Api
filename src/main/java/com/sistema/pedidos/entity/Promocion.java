package com.sistema.pedidos.entity;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "Promocion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
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

    @OneToMany(mappedBy = "promocion")
    private Set<Plato> platos;
}
