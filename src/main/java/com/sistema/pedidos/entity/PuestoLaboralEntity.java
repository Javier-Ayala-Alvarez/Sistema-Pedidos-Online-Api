package com.sistema.pedidos.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "puesto")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PuestoLaboralEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    @Column
    private String nombre;
    @Column
    private Boolean estado;
}
