package com.sistema.pedidos.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "cliente")
@Data
public class ClientesEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer idCliente;
    @Column
    private String nombre;
    @Column
    private String apellido;
    @Column
    private boolean estado;
    @OneToOne
    @JoinColumn(name = "usuario_id" , nullable = false)
    private Usuario usuario;


}
