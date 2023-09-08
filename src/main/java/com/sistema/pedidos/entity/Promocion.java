package com.sistema.pedidos.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "Promocion")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Promocion implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Promocion")
    private  long id;
    @Column(name = "PR_Nombre")
    private  String nombre;
    @Column(name = "PR_Cantidad")
    private Integer cantidad;
    @Column(name = "PR_Porcentaje")
    private Double  porcentaje;
    @Column(name = "PR_Estado")
    private Boolean estado;

    @OneToMany(mappedBy = "promocion")
    private Set<Plato> platos;
}
