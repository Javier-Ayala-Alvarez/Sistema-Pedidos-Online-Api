package com.sistema.pedidos.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.Evento;
import com.sistema.pedidos.entity.PlatoProducto;
import com.sistema.pedidos.entity.Promocion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductDTO implements Serializable {
    private long id;
    private  String nombre;
    private  String descripcion;
    private Double precioVenta;
    private  Double ganancia;
    private  boolean estado=true;
    private  String urlImagen;
    private CategoriaDTO category;
}