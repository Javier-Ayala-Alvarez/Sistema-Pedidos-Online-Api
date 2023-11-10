package com.sistema.pedidos.DTO;

import lombok.*;

import java.io.Serializable;
import java.math.BigInteger;

@Data
public class ResportVentaDTO implements Serializable {
    private BigInteger id;
    private String nombre;
    private String direccion;
    private Double ventas;

}
