package com.sistema.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "producto")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_Producto")
    private long id;
    @Column
    private  String nombre;
    @Column
    private  String descripcion;
    @Column
    private  boolean estado=true;
    @Column
    private Double precioVenta;
    @Column
    private  Double ganancia;
    @Column
    private  String urlImagen;
    @ManyToOne
    private Category category;
    @JsonIgnore
    @ManyToOne
    private Evento evento;
    @ManyToOne
    private  Promocion promocion;
    @OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PlatoProducto> platoProducto;

}
