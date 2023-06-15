package com.sistema.pedidos.DTO;

import lombok.Data;

@Data
public class UsuarioClienteDTO {
    Long idCliente;
    String nombre;
    String apellido;
    Boolean estado;

    UsuarioDIO usuario;

    // id not null validation method
    public boolean isIdClienteValid() {
        return this.idCliente != null;
    }
    // is all parameter complete
    public boolean isAllParamComplete() {
        return this.idCliente != null && this.nombre != null && this.apellido != null && this.estado != null && this.usuario != null;
    }


}
