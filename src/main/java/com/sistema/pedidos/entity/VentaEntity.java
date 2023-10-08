package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "ventas")
@Data
public class VentaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idVenta;
    @Column
    private String nombreEncargado;
    @Column
    private String correoEncargado;
    @Column
    private String numeroTelefono;
    @Column
    private String  otrasIndicaciones;
    @Column
    private Double  total;
    @OneToOne
    @JoinColumn(name = "usuario_id" , nullable = false)
    private Usuario usuario;
    @OneToOne
    @JoinColumn(name = "sucursal_id" , nullable = false)
    private Sucursal sucursal;
    @Column
    private Double altitud;
    @Column
    private Double longitud;
    @Column
    private String estado; //p = pedido; c = preparando en cocina; d = despacho; e = entregado al cliente
    @Column
    private String comentarioEntrega;

}