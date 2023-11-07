package com.sistema.pedidos.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DetalleDeVentaDTO implements Serializable {
    private  String imagen;
    private  String nombre;
    private  Double precio;
    private  Integer cantidad;
    private  Double total;
}
