package com.sistema.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "plato_producto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PlatoProducto implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JsonBackReference
    @JoinColumn(name = "plato_id", nullable = false)
    private Plato plato;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "producto_id" , nullable = false)
    @JsonBackReference
    private Product producto;

    @Column (name = "fecha_registro", nullable = false)
    private Date fechaRegistro;

}
