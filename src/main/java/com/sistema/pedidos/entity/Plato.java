package com.sistema.pedidos.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "plato_menu")
public class Plato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private long id;
    @Column (name = "nombre")
    private String nombre;
    @Column (name = "descripcion")
    private String descripcion;

    @Column (name = "estado")
    private boolean estado;

    @Column (name = "precio_total")
    private Double precio;

    @Column (name = "url_imagen")
    private String urlImagen;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "categoria_id")
    private Category categoria;

    @OneToMany(mappedBy = "plato", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<PlatoProducto> platoProducto;


    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "promocion_id")
    @JsonBackReference
    private Promocion promocion;


}
