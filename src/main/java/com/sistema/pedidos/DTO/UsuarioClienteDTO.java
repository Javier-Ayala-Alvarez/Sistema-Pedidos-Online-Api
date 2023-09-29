package com.sistema.pedidos.DTO;

import lombok.Data;

import javax.persistence.Column;
import java.util.Date;

@Data
public class UsuarioClienteDTO {
    private Long idCliente;
    private String nombre;
    private String apellido;
    private Boolean estado;

    private UsuarioDIO usuario;
    private String apodo;
    private Date fechaNacimiento;


    // id not null validation method
    public boolean isIdClienteValid() {
        return this.idCliente != null;
    }
    // is all parameter complete
    public boolean isAllParamComplete() {
        return this.idCliente != null && this.nombre != null && this.apellido != null && this.estado != null && this.usuario != null;
    }


}
