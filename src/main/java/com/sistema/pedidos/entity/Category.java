package com.sistema.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column(name = "categoria_nombre")
    private String CT_Nombre;
    @Column(name = "categoria_estado")
    private boolean CT_Estado=false;


    @Column(length = 1000)
    private String img;

    @JsonManagedReference
    @OneToMany(mappedBy = "categoria")
    private List<Plato> platosList;
}
