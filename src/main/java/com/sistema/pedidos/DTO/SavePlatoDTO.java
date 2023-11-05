package com.sistema.pedidos.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter



public class SavePlatoDTO {
    private Long id;
    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;
    @NotBlank(message = "La descripcion es obligatoria")
    private String descripcion;
    @NotBlank(message = "La url de la imagen es obligatoria")
    private String urlImagen;
    @NotEmpty(message = "La lista de productos es obligatoria")
    private List<Long> listaProductos;
    @NotNull(message = "El id de la categoria es obligatorio")
    private Long idCategoria;

    private Long idPromocion;
    @NotNull(message = "El precio es obligatorio")
    private Double precio;

    public boolean nonNullPromocion() {
        return this.idPromocion != null;
    }


}
