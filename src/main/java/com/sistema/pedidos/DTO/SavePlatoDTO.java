package com.sistema.pedidos.DTO;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class SavePlatoDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private String urlImagen;
    private List<Long> listaProductos;
    private Long idCategoria;
    private Long idPromocion;
    private BigDecimal precio;

    public Boolean nonNullFields(){
        return nombre != null && descripcion != null && urlImagen != null && listaProductos != null && idCategoria != null  && precio != null;
    }
    public Boolean nonNullFieldsUpdate(){
        return id != null && nonNullFields();
    }
    public Boolean nonNullPromocion(){
        return idPromocion != null;
    }
}
