package com.sistema.pedidos.DTO;

import lombok.Data;

@Data
public class UsuarioClienteDTO {
    String nombre;
    String apellido;
    Boolean estado;

    UsuarioDIO usuario;

}
