package com.sistema.pedidos.DTO;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.sistema.pedidos.entity.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Data
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