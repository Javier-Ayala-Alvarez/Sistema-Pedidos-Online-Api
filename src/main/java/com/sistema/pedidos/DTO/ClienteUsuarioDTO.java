package com.sistema.pedidos.DTO;

import lombok.Data;

@Data
public class ClienteUsuarioDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private String correo;
    private String usuario;
    private Boolean estado;
}
