package com.sistema.pedidos.DTO;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class VentasDeliveryDTO {

    private Long id;
    private String nombreEncargado;
    private String correoEncargado;
    private String numeroTelefono;
    private String  otrasIndicaciones;
    private Double  total;
    private Double altitud;
    private Double longitud;
    private String estado; //p = pedido; c = preparando en cocina; d = despacho; e = entregado al cliente
    private ProductDTO producto;
    private PlatoDTO plato;



}
