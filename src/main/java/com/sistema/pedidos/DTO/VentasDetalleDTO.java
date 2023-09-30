package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.Product;
import com.sistema.pedidos.entity.VentaEntity;
import lombok.Data;

import javax.persistence.*;
@Data
public class VentasDetalleDTO {

    private long idVentaDetalle;

    private Integer cantidad;

    private double precioUnitario;

    private double precioTotal;

    private ProductDTO product;
    private  PlatoDTO platoDTO;

    private VentasDTO ventaEntity;
}
