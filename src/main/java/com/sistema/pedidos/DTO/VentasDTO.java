package com.sistema.pedidos.DTO;

import com.sistema.pedidos.entity.Sucursal;
import com.sistema.pedidos.entity.Usuario;
import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VentasDTO {

    private long idVenta;
    private String nombreEncargado;

    private String correoEncargado;

    private String numeroTelefono;
    private String  otrasIndicaciones;
    private Double  total;

    private UsuarioDTO usuarioDTO;

    private Sucursal sucursal;
    private Double altitud;
    private Double longitud;
    private String estado; //p = pedido; c = preparando en cocina; d = despacho; e = entregado al cliente
    private String comentarioEntrega;
    private Date fecha;

}
