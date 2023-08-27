package com.sistema.pedidos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class PromocionDTO {
    private  Long id;
    private  String nombre;
    private Integer cantidad;
    private Double porcentaje;
}
