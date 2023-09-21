package com.sistema.pedidos.DTO;

import lombok.Data;

import java.math.BigDecimal;
import java.math.BigInteger;

@Data
public class ProductoPlatoDTO {
    private BigInteger idProducto;
    private String nombre;
    private String descripcion;
    private Boolean estado;
    private Double precioVenta;
    private Double ganancia;
    private String urlImagen;
    private String evento;
    private BigInteger promocion;
    private BigInteger idCombo;
    private String nombreProducto;
    private String urlImagenProducto;
    private BigInteger category;
}
