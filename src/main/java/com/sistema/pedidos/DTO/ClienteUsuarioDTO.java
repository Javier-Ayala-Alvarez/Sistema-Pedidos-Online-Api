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
    private String password;

    //is all parameter complete
    public boolean isParamComplete() {
        return this.nombre != null && this.apellido != null && this.correo != null && this.usuario != null  && this.password != null;
    }
}
