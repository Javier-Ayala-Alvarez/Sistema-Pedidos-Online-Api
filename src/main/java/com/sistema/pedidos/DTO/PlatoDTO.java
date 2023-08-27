package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.Category;
import com.sistema.pedidos.entity.PlatoProducto;
import com.sistema.pedidos.entity.Promocion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class PlatoDTO {
    private long id;

    private String nombre;
    private String descripcion;
    private boolean estado;
    private Double precio;
    private String urlImagen;
    private CategoriaDTO categoria;
    private List<ProductDTO> productos;
    private PromocionDTO promocion;

}
