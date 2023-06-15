package com.sistema.pedidos.DTO;

import lombok.Data;

@Data
public class UsuarioDIO {
    Long idUsuario;

    String username;
    String email;
    String password;
    Boolean estado;

    // id not null validation method
    public boolean isIdValidacionesValid() {
        return this.idUsuario != null;
    }

    // is all parameter complete
    public boolean isAllParamComplete() {
        return  this.username != null && this.email != null && this.password != null;
    }

}
