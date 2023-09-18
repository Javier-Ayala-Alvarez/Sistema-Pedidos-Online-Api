package com.sistema.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "categoria")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Category implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;
    @Column(name = "categoria_nombre")
    private String nombre;
    @Column(name = "categoria_estado")
    private Boolean estado = false;


    @Column(length = 1000)
    private String img;

    @JsonIgnore
    @JsonManagedReference
    @OneToMany(mappedBy = "categoria")
    private List<Plato> platosList;
}


